package com.yoti.api.client.docs.session.retrieve;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentResourceResponse extends ResourceResponse {

    private String documentType;
    private String issuingCountry;
    private List<PageInfo> pages;
    private DocumentFieldsResponse documentFields;

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }

    public void setIssuingCountry(String issuingCountry) {
        this.issuingCountry = issuingCountry;
    }

    public List<PageInfo> getPages() {
        return pages;
    }

    public void setPages(List<PageInfo> pages) {
        this.pages = pages;
    }

    public DocumentFieldsResponse getDocumentFields() {
        return documentFields;
    }

    public void setDocumentFields(DocumentFieldsResponse documentFields) {
        this.documentFields = documentFields;
    }

}
