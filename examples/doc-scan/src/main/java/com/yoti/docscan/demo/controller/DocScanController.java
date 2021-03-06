package com.yoti.docscan.demo.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import com.yoti.api.client.Media;
import com.yoti.api.client.docs.DocScanClient;
import com.yoti.api.client.docs.DocScanException;
import com.yoti.api.client.docs.session.create.CreateSessionResult;
import com.yoti.api.client.docs.session.retrieve.GetSessionResult;
import com.yoti.docscan.demo.service.DocScanService;
import com.yoti.docscan.demo.session.DocScanUserSession;

import org.apache.tika.config.TikaConfig;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@EnableWebMvc
@Configuration
@ConditionalOnClass(DocScanClient.class)
public class DocScanController implements WebMvcConfigurer {

    private static final Logger LOG = LoggerFactory.getLogger(DocScanController.class);
    private static final String DOC_SCAN_SESSION_ID = "DOC_SCAN_SESSION_ID";

    private final DocScanService docScanService;

    public DocScanController(DocScanService docScanService) {
        this.docScanService = docScanService;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @GetMapping
    public String getIndex(final Model model, HttpSession httpSession) {
        CreateSessionResult sessionResult = null;
        try {
            sessionResult = docScanService.createSession();
        } catch (DocScanException e) {
            LOG.error(e.getMessage());
            model.addAttribute("error", e.getMessage());
        }

        if (sessionResult == null) {
            return "error";
        }

        httpSession.setAttribute(DOC_SCAN_SESSION_ID, sessionResult.getSessionId());

        model.addAttribute("iframeUrl", docScanService.getIframeUrl(sessionResult));
        return "index";
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String getUserSession(final Model model, HttpSession httpSession) {
        String sessionId = (String) httpSession.getAttribute(DOC_SCAN_SESSION_ID);

        if (sessionId == null || sessionId.equals("")) {
            return "redirect:/";
        }

        GetSessionResult sessionResult = null;
        try {
            sessionResult = docScanService.getSession(sessionId);
        } catch (DocScanException e) {
            LOG.error(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "error";
        }

        if (sessionResult == null) {
            return "error";
        }

        model.addAttribute("sessionResult", sessionResult);

        return "success";
    }

    @RequestMapping(value = "/media", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getMedia(
            @RequestParam(value = "mediaId") String mediaId,
            @RequestParam(value = "base64", required = false, defaultValue = "0") String base64,
            HttpSession httpSession) {

        String sessionId = (String) httpSession.getAttribute(DOC_SCAN_SESSION_ID);

        Media media = null;
        try {
            media = docScanService.getMedia(sessionId, mediaId);
        } catch (DocScanException e) {
            LOG.error(e.getMessage());
        }

        if (media == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (base64.equals("1")) {
            byte[] decoded = Base64.decode(media.getContent());
            InputStream is = new BufferedInputStream(new ByteArrayInputStream(decoded));
            TikaConfig config = TikaConfig.getDefaultConfig();
            try {
                org.apache.tika.mime.MediaType mediaType = config.getMimeRepository().detect(is, new Metadata());
                MimeType mimeType = config.getMimeRepository().forName(mediaType.toString());
                String extension = mimeType.getExtension();

                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=facemap" + extension);

                return ResponseEntity.ok()
                        .headers(headers)
                        .contentLength(decoded.length)
                        .contentType(MediaType.parseMediaType(mimeType.toString()))
                        .body(decoded);
            } catch (IOException | MimeTypeException e) {
                LOG.error(e.getMessage());
                return ResponseEntity.status(500).build();
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(media.getMimeType()));

        return new ResponseEntity<>(media.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/error")
    public String showErrorPage(final Model model, @RequestParam(value = "yotiErrorCode", required = false) String yotiErrorCode) {
        model.addAttribute("error", "An unknown error has occurred");

        if (yotiErrorCode != null && !yotiErrorCode.equals("")) {
            model.addAttribute("error", String.format("Error Code: %s", yotiErrorCode));
        }

        return "error";
    }

}
