package com.yoti.api.client.spi.remote;

import static java.lang.Boolean.TRUE;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.yoti.api.client.Attribute;
import com.yoti.api.client.Profile;
import com.yoti.api.client.spi.remote.proto.AttrProto.Anchor;

public class SimpleProfileTest {

    private static final String YOTI_ADMIN_VERIFIER_TYPE = "YOTI_ADMIN";

    private static final String PASSPORT_SOURCE_TYPE = "PASSPORT";

    private static final String DRIVING_LICENCE_SOURCE_TYPE = "DRIVING_LICENCE";

    private static final String GIVEN_NAMES_ATTRIBUTE = "given_names";
    private static final String SOME_KEY = "someKey";
    private static final String STARTS_WITH = "startsWith";
    private static final String STRING_VALUE = "test value";
    private static final Integer INTEGER_VALUE = 1;
    
    
    private static final String DL_ANCHOR = "CjdBTkMtRE9Dz8qdV2DSwFJicqASUbdSRfmYOsJzswHQ4hDnfOUXtYeRlVOeQnVr3an"
                                    + "ESmMH7e2HEqAIMIIEHDCCAoSgAwIBAgIQIrSqBBTTXWxgGf6OvVm5XDANBgkqhkiG9w0"
                                    + "BAQsFADAuMSwwKgYDVQQDEyNkcml2aW5nLWxpY2VuY2UtcmVnaXN0cmF0aW9uLXNlcnZ"
                                    + "lcjAeFw0xODA0MDUxNDI3MzZaFw0xODA0MTIxNDI3MzZaMC4xLDAqBgNVBAMTI2RyaXZ"
                                    + "pbmctbGljZW5jZS1yZWdpc3RyYXRpb24tc2VydmVyMIIBojANBgkqhkiG9w0BAQEFAAO"
                                    + "CAY8AMIIBigKCAYEA3u2JsiXZftQXRG255RiFHuknxzgGdQ1Qys6O+/Dn/nwEOPbzGBn"
                                    + "4VTMfT1tCl7lD96Eq/qf0v3M6jLWQNJYqt7FbqlH0qtfQLT8fHX04vKwWkJdAvcpOSVd"
                                    + "1i2iyO5wVsvoXCt2ODyMGhd7/6qHeNZei50ARV8zF8diqneNq87Fgg1seuF+YEVAj14y"
                                    + "bjNmTk+MQvKkONSh2OPYNYeF/2H+0pXNe+MXhyY+vJlcRrqXLS52s4VjdeksVc05o/oe"
                                    + "NVckeqgmNhmEnLUNRGQFNOptrB0+g+hcdDQBFOkgeS/dS8iiMp5VQUShKOyQ5/twWOEQ"
                                    + "oJ3ZYRZGIyN8cErUfOUCQBwJOfdspMgbwom3//b5z9+alNOeZDOQRkI5vgvV8s+CvtSn"
                                    + "nMVt9WZMXmY+4uUP9/wZXmw2oBwlJmS9kUKslIHiMNzU07t1y6xMUMhYugxR5GatSN5k"
                                    + "H+36ylJATWVyuuj3Ub/q88cnaiT0jYtsAS4cpJUcEi60+j8qyuc5dAgMBAAGjNjA0MA4"
                                    + "GA1UdDwEB/wQEAwIDmDAiBgsrBgEEAYLwFwEBAQQTMBGAD0RSSVZJTkdfTElDRU5DRTA"
                                    + "NBgkqhkiG9w0BAQsFAAOCAYEANly4rGh8NaE3OwX54kOB8WBO2z/FBDDSi5VByHmMl4V"
                                    + "Pd8Pz26F1kS8qhcKjG6DuaX5UnX33GM6DuLv3nP3uiWEnv/lcitma2LC+qgJp4ItCw2E"
                                    + "MBLiof+dKzms4HqTHyKcPBpxBO6RPkvY5YQDEF0YiW17O31O2ltZTsc9ZsX5M1IiVwbO"
                                    + "ieTDtHy2M/K6Bol/JU/H/L1lAfpZ7khADZmEymjh/6Aw2v18Re37SWl86HxU4t862VNf"
                                    + "ogWO1nlgmgEwoCDgQ6OzR6dhGHJQfXymCJCB3wpA2x3i9rd2L8qrzxX9p5uInCK4+WKS"
                                    + "mhggB31s6dJwS5vAp5D6/i19aMgJqVFfxq/FUA1wkx/flgoC/Xb8MMTDTLo4/ekINdXX"
                                    + "jbQboVii2PGZKAK6FQNZ0FYC7WlA65gBBCZzvQ8imLwBQuy/kLvWbWXVDF5lzMdohijB"
                                    + "nuo4O4fenbAcy51CUvxAjgK7G9FQCyZ39gCPrpy3VVAcjbr9Njk15plcs1yAbGoUDCAE"
                                    + "SgAO1NMBkegQwBTWooNohw8CgIQhfq6dqolvIYDlBIFWThZo34qmRIQe2KKS4SCrxHT5"
                                    + "syjX0X1jtmHPIjZNifbiEAy7Jzzn1xlNWIwetnVoJBcnNumx4r0nmqRrCkRZLlgP4wwM"
                                    + "hwBV56X4TQOUMF8H1ESfmrWIMM9O+vhEJB5QuoAFRPaMcNkYTvbeAvAkhwxfbb8Ac3IW"
                                    + "JPakxORI8jeSop73yc9blxfV1D2ki4yjB2fI7uEXkRBOP/IQ301e7m+fQFLTZ1m1nZiz"
                                    + "Hh+s5GBcApwn92AsfRvgRnSXrc24qoqqvthm4fp9RbnO0d89RqO4Pxu6f1y9BqJ5RMhV"
                                    + "A6Vl+5vsU0nNhiH4Jki9N8dGmX3CTnwf51VUK5aeQwLIgCWaPjE4xC7YX9Fd8WUnsp1/"
                                    + "JllMhAQF7fym40usrHuVt9htd5E2p8zxRidA8NqWNV2rXTGWO5hUSwCAMdfgz431BZSO"
                                    + "fLPZHHg+g4qu+dcLerBqvMggVQLsGB10omwv4oJwiACqFAwgBEoADohVhusZuxzj2ldV"
                                    + "MOKIw+v59l/vWwSgHEIYbIcHNg03EHNLWA7EzrEny+jXyaKERPK8pxASewVJTQo3qYm3"
                                    + "Ezr9QuEy5XG2WfATe1OZuchJxK+IpHRN7o1ZxHf9cCXa22KA4bAKUgb/gSKC6hr9bjMu"
                                    + "06qyb/P+TzWNLTv4OX51dE6iI4WwltsQnPg4BRcrWjvoqkgPi1AKVd+no4J3H2tc0b7a"
                                    + "s/KJCPgR7HMTtuxp/eooR0zPRB/bZFkywrdGbCECshb11G+j1iBYaFHc1ewcmcNjufZV"
                                    + "bZ60pR4JfZUcpiRZJO13ZNnfX7ugc2vK/tL1hM963Y4BfvKXnmQeiLojlpilPxOFET+n"
                                    + "1yodR8J/i1GWzV41Nwx2PFEQv0VofkOZp28mHgQsAM8omReGZqyKEf+oAWjFWY0l1M88"
                                    + "3URQSr0CV04U6iSbS6qeSzL5YkP4CNny0n4Pt79UJWyVA+nHAThnsz4relhfk82At5IL"
                                    + "ASx2zgOkeIJVm5UnTC2ywMkcIARDR0uX8mLLaAhocZv/4kdenjmzEE1nkHW7ks7qh+II"
                                    + "J0YbSPwVkGiIc7BbgXGE8cSGwKuul83Yy/z1InbhBl2B1drEuOjoA";
    
    private static final String PP_ANCHOR = "CjdBTkMtRE9D5oQ/YdIfjbvf1HL/HT7s/Xgse6TlNthXYyfF9knv02vq6Vxd5R"
                                    + "afiJbR9xVVl+knEowIMIIECDCCAnCgAwIBAgIRANEL6idR0hcevQr4tmIIcoowDQYJKo"
                                    + "ZIhvcNAQELBQAwJzElMCMGA1UEAxMccGFzc3BvcnQtcmVnaXN0cmF0aW9uLXNlcnZlcj"
                                    + "AeFw0xODA0MDUxNDM1MDFaFw0xODA0MTIxNDM1MDFaMCcxJTAjBgNVBAMTHHBhc3Nwb3"
                                    + "J0LXJlZ2lzdHJhdGlvbi1zZXJ2ZXIwggGiMA0GCSqGSIb3DQEBAQUAA4IBjwAwggGKAo"
                                    + "IBgQC9q8ZJxaOoeDS5anGhVhQ6Y0Ge47Jv0pmXoaI+rNoO6zkErmJyL2sLNJRRrH2+aq"
                                    + "TKXwnjCF10EBld/0ryoOI1Zin6UfuEIi3uCXAVktb8qkpX+JJH+6FRZ0QztNUybfWN2M"
                                    + "1BP3P1P3i7jO5Vh7BsQG7WEB8hhn6gAGP/aWaBk79i6Om2/m6qpPCHM9wSDM+L+bpJdr"
                                    + "wRgZEdHzyOpMKxUwpIe0D0j6M9e+8gSVnK40aRlIXdjTrmggncDcd9CMRN1oIFJ9YDLF"
                                    + "RUYKFp5Hjgfiv2k0uIdyJDOx65VRVROxpfZjh2jgLchr4FBY/WCP8AA8G/usS9EiwRQx"
                                    + "Z8+bf/4naJXVFMRWdNLRNX3g7pNZkmLFt6prwOCc9PijLIKlKX3uvjJgAm3/g28VON0g"
                                    + "9ys8c4LVLBUg9tYvWtJg2+yNWG7sRr2U0mohTiYWUnf4gnhvsxTNVTWvOY4FltZnJOLl"
                                    + "KoaSTyfTIjIGAvFB8P3s3lZDXzRG3QCtInUkASgOUCAwEAAaMvMC0wDgYDVR0PAQH/BA"
                                    + "QDAgOYMBsGCysGAQQBgvAXAQEBBAwwCoAIUEFTU1BPUlQwDQYJKoZIhvcNAQELBQADgg"
                                    + "GBAE/aVEbzKLjDowg6TbRetXoumWbeqhL4y1rkFz6Oig4TutaSLEdIfqzONBa9bfimcJ"
                                    + "cVyq5PVASflNv770DGMwC5qPj6vFTKWjgMp7e/t7iPnuMic7LlIEVOtZS+eQBCYdBfwC"
                                    + "2nY/gTqTaDZdHmK3QPyLyUjcQNplrgdqsk5jekQ3lYnbYUzSm9dLQjxkcAtCq0Ud6fM/"
                                    + "GGkDH7wB+WHx6gDAlT3KhPLypkg0tGI8/Ej01FNrfaN7LKWWxfVGXwNjS/HpPJvACjR7"
                                    + "wp6asJErO+jUItKvZ772A0AUiOSKjgUJ3NyrYczmxds4IE7bnsedkHsgRc9PDJraGHKr"
                                    + "hXyDfZzgPzJ4zQ1iQXx4PicR7Dm7NyeA1zepFW2azRFvht3ge0bKUM+/CuR9GV9HOirX"
                                    + "XSEAUTv//S5M3REMJJbstd3tVPR48gpcKWXqUPicg+E8JLCxKvXw+R1OK9yqlW6bnQfU"
                                    + "SvI2SafYkixeyHnmk7kP9sAkvSi29oH8n1YH4hPxqFAwgBEoADAdw/1ZI5sbf+2H/tvy"
                                    + "EVNmsAjmFHafiKhG2e7c6TmISEXfFTJTi69lT/DBgSHlhxzwpBl3Mc7MEqobd4SX5PBb"
                                    + "RzqaGdiWt00C2T359hH0+tHUvxwRq3lTpWoLQ9rsZD0m8fHUYrtv4hrQeipeq7uVoUNm"
                                    + "c0vo/Yp6+6lkRECGss3k8/J4rXwrhciBYEuKqhChkXZwbKVU83IbioVRBnbesvNoE0Ww"
                                    + "gbcx7+1VAVaDC6zmZ/cmUMdwdsIkT4MXV5FqTlqVc7kRhiLf/iNPEr806mYvR3z26JO8"
                                    + "VIjPKKvgoWYucH5g5GFYukpJaG+O3s9wgarmkrhcsx74gitTMgjRYiWSQQ02wpUnj6WW"
                                    + "PQ5Zsm6RTcdt9Q3oHxdzWm5DCeMXuS+r0RgGpz4p749uuIGvzs6gJAiR4ye3o22gU/SE"
                                    + "6+sGjtc2i0ddjqRjxgmxsSNL9dIy07kDqZ/mK5P4TCxhUPmOYxjhfndl1dBCQleEV0Pp"
                                    + "MmXXUaKVlCVA+/62PMIgNPQ1IqhQMIARKAA5Q1xoxg3Fq34i3km+zKiU4tpaAcxB//fc"
                                    + "RjcXVOvSaJvWvLMMcBkPlny5+lM3fTb8uzs6RMNEWrb+GD3gVbnrzx5Bbc2f/lJlU0EG"
                                    + "s0ZsBzSuWsr0qPiYd/oMtXu2Iz3oR8t7C5whUZX9rBlayrm+AceLFJOLdTkVFx8qwJe1"
                                    + "0brMqoE/1OU4403SILzIkw+nsOKAmjFlymhRZwwDEmBFBf+v8vyDLDeVM8EtmtTLM/FH"
                                    + "pgCPsNBL+9UnwHSC+np4kIS3sJMNXHuoS0uxpi/XgFlZSWjPnR8UKzw1iXzA7Dz18Msf"
                                    + "v+aHHUF/EtML3SJwDv52ewP6cv6N9pd5XtxJB9D4nB959t7oNTltQKGoIy5wCNOITVo7"
                                    + "CzXX7IBwE3Lzp+uvJuetEkEVgjGmUD6PTSK0P4yL56cWwW30jUHXNTkN64ryHhwKvHdv"
                                    + "zT+xp/synMnLnPO8X6+BV6sqm7GF+OL4PGE3XO3nZCIPwZ0dgxz6r6BtkfV7pBWIlPPa"
                                    + "/2LTJHCAEQ0bvEyui02gIaHFIc8RKJ4U36MiJqXMjQlWXbhVu/URDuYOFXITEiHNs5Ua"
                                    + "Z0Q8FPlpgca5LurwwVkP/EqVsqzc1tuK06AA==";
    
    private static final String YOTI_ADMIN_ANCHOR = "CjdBTkMtRE9DJrhhgGLoPILLZozIid4Aoiw/hLolQRF95pGqqsok3x"
                                    + "facAZQ9bJQD6JVzYPutOAIEpwIMIIEGDCCAoCgAwIBAgIRAMEOn91ajjMKgwOfw//2iI"
                                    + "0wDQYJKoZIhvcNAQELBQAwLjEsMCoGA1UEAxMjZHJpdmluZy1saWNlbmNlLXJlZ2lzdH"
                                    + "JhdGlvbi1zZXJ2ZXIwHhcNMTgwNDA1MTQyNzM2WhcNMTgwNDEyMTQyNzM2WjAuMSwwKg"
                                    + "YDVQQDEyNkcml2aW5nLWxpY2VuY2UtcmVnaXN0cmF0aW9uLXNlcnZlcjCCAaIwDQYJKo"
                                    + "ZIhvcNAQEBBQADggGPADCCAYoCggGBAN7tibIl2X7UF0RtueUYhR7pJ8c4BnUNUMrOjv"
                                    + "vw5/58BDj28xgZ+FUzH09bQpe5Q/ehKv6n9L9zOoy1kDSWKrexW6pR9KrX0C0/Hx19OL"
                                    + "ysFpCXQL3KTklXdYtosjucFbL6Fwrdjg8jBoXe/+qh3jWXoudAEVfMxfHYqp3javOxYI"
                                    + "NbHrhfmBFQI9eMm4zZk5PjELypDjUodjj2DWHhf9h/tKVzXvjF4cmPryZXEa6ly0udrO"
                                    + "FY3XpLFXNOaP6HjVXJHqoJjYZhJy1DURkBTTqbawdPoPoXHQ0ARTpIHkv3UvIojKeVUF"
                                    + "EoSjskOf7cFjhEKCd2WEWRiMjfHBK1HzlAkAcCTn3bKTIG8KJt//2+c/fmpTTnmQzkEZ"
                                    + "COb4L1fLPgr7Up5zFbfVmTF5mPuLlD/f8GV5sNqAcJSZkvZFCrJSB4jDc1NO7dcusTFD"
                                    + "IWLoMUeRmrUjeZB/t+spSQE1lcrro91G/6vPHJ2ok9I2LbAEuHKSVHBIutPo/KsrnOXQ"
                                    + "IDAQABozEwLzAOBgNVHQ8BAf8EBAMCA5gwHQYLKwYBBAGC8BcBAQIEDjAMgApZT1RJX0"
                                    + "FETUlOMA0GCSqGSIb3DQEBCwUAA4IBgQBxLhUfuENJyH6+kkF7d6rEw1B+hREojZmlw6"
                                    + "OXjo43CEwt1bGy6/qKtDhMej2g1HcLRv/2uQYyrHLjyfqP3YiLSiXkPcbl+aJ1SWiOJW"
                                    + "/hepagSmnukkx3xvXrNagusKEO0Z+MhTCz3Ma2jC/0Dzl0PdxOkQ+Hwteebgk9kqeJmY"
                                    + "lZtEBWbNLh5mcS9Is83zDDsH8Uf/Dg/EfRcd1cGGoe3ceyp0wt6n7U1oTA6aRSEAhYVL"
                                    + "OemmBgSrg1db3crsNvF92T+wnTM4U/ao3q4WTjNbQCHI/C/zdqel+qOmYVzPdcJNSFkS"
                                    + "SqR2mDL3IJfh2oA5XnwMo1Tah4q6PWilifZDLMQw8ooLo2ZfSVS0IZqmp8tJKsOsWFZO"
                                    + "Mp7h2ajiApSedGkAmFeQvs5zMbPSCVamAc3uP3ZkEz/8T/e0FEed7Kb5mtIJmnedbvcv"
                                    + "2mkFOyyT1e6Xvb0BSUOnDa0Bj5c2L4DaLr2dWytKkCqfpCwZPbA6D+Zm/wn9G7lVgjVH"
                                    + "IahQMIARKAAzfc9GZMSEqdUL5m8jFcwfIAE3tqM1rzp0GknciT8CkFdiXSd6kmcmWv2X"
                                    + "UYP14VQWJSwneIZg9Fk0ITqUZpZ4IqqpuHfDevc8fU7quuc7mN1LXy2VpfyMhWsiV/N0"
                                    + "cwh2bUKF2dJsaOClv4KfE84rw+p1XGaron2/px9BFV+zTgggPN3I1LXCmAWWA8vvOJY1"
                                    + "F+yhsf06Wn0820XK3ddLedRY62mJnFYkhhLfreyoz/SOhkpY6s7LUJm4i9OmMq6j4o8l"
                                    + "hRRETdbYkaCPxdVOWBTHiuQYQACQb8M5BQIFNiyvl7STKRIuhuOefcq2Y6GiQWok3e32"
                                    + "NDwEDIGdSbnrYGLT7OnuBoLIpVT6YqRMOt1A+ZSTxom/Xrts4yivLvuIqMdMM4R2fg/G"
                                    + "8XxGi4Y0Hq/XWKVOEVgxSkkmC2EvQilncC6SohT5Gv6pJHAzEhMugle2q4kGHAqKX5Yc"
                                    + "RNtxX3ndEmMUCT4t6t7KsGDCPFIuutMB9DNxQirbyqsI5A3iIAKoUDCAESgANwZASCFu"
                                    + "n9iHDRmadUWkaIVmj72yLQFSEpevo0XPy/q8rnw46HNDsgVsDjC8LP1PVGoSY8uBIspU"
                                    + "Djg2vu2qMT6D5+GJ3aN19legUkA2+FK37G/YOpix/wPjCJqB2xAn/KaWM9FV9Vgh2xo3"
                                    + "UN4EUU9F5lVsRCUaZtFhWOeHApBfYgFghW3WivNDwGibkW668E0kLd/7+29MlXP+yXN4"
                                    + "P7/7YtCzskSXCIztzbQ2iyHHw88xWaVmWNr0p5j32kClsdrHc1YlQQpTnsKD2sSAyXMx"
                                    + "8cRfAtcHgvvciwgGrOzy2iTiQ/6cRRIwvM0RbkXhRJGGE1w0LMWQTPOXA/0xniCLVHzB"
                                    + "VeXdXsBmWDTcfQDXgE+Q3kZy5XyjtAzYPv4YlBogvsAT1P/DKDq/GBgT7KARuHPaVLMq"
                                    + "nbll+D4Z6aa9HApxMpyW5ptvP4UBuP824fUBJc9+2VUG8Am63nBN6hrm8+lwoheSPydw"
                                    + "b185Qe6PWL4Jl+DvbzN2C0wsUFKRQyRwgBEIaQ8PyYstoCGhyG6joGfHdvA8tGS+Ol98"
                                    + "igUHdLW56nhnGLovTMIhz+RsUWrtszSjWSim2/4vJAE8QjXJ98ou4AVzKUOg9EUklWSU"
                                    + "5HX0xJQ0VOQ0U=";
    
    @Test(expected = IllegalArgumentException.class)
    public void constructor_shouldFailConstructionForNullAttributes() {
        new SimpleProfile(null);
    }

    @Test
    public void is_shouldReturnBooleanValueForExistingKey() {
        Profile profile = new SimpleProfile(asList(SOME_KEY, TRUE));

        boolean result = profile.is(SOME_KEY, false);

        assertTrue(result);
    }

    @Test
    public void is_shouldReturnDefaultBooleanForNonExistingKey() {
        List<Attribute> list = Collections.emptyList();
        Profile profile = new SimpleProfile(list);

        boolean result = profile.is(SOME_KEY, false);

        assertFalse(result);
    }

    @Test
    public void is_shouldReturnDefaultBooleanForMismatchingType() {
        Profile p = new SimpleProfile(asList(SOME_KEY, "String"));

        boolean result = p.is(SOME_KEY, false);

        assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void is_shouldThrowExceptionForNullAttributeName() {
        List<Attribute> list = Collections.emptyList();
        Profile profile = new SimpleProfile(list);

        profile.is(null, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAttribute_shouldThrowExceptionForNullAttributeName() {
        List<Attribute> list = Collections.emptyList();
        Profile profile = new SimpleProfile(list);

        profile.getAttribute(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAttributeTyped_shouldThrowExceptionForNullAttributeName() {
        List<Attribute> list = Collections.emptyList();
        Profile profile = new SimpleProfile(list);

        profile.getAttribute(null, String.class);
    }

    @Test
    public void getAttribute_shouldReturnStringValueForExistingKey() {
        Profile profile = new SimpleProfile(asList(SOME_KEY, STRING_VALUE));

        String result = profile.getAttribute(SOME_KEY);

        assertEquals(STRING_VALUE, result);
    }

    @Test
    public void getAttribute_shouldReturnNullValueForNonExistingKey() {
        List<Attribute> list = Collections.emptyList();
        Profile profile = new SimpleProfile(list);

        assertNull(profile.getAttribute(SOME_KEY));
        assertNull(profile.getAttribute(SOME_KEY, Integer.class));
    }

    @Test
    public void getAttribute_shouldReturnNullValueForMismatchingType() {
        Profile profile = new SimpleProfile(asList(SOME_KEY, INTEGER_VALUE));

        assertNull(profile.getAttribute(SOME_KEY));
        assertNull(profile.getAttribute(SOME_KEY, String.class));
    }

    @Test
    public void getAttribute_shouldReturnIntegerValueForExistingKey() {
        Profile profile = new SimpleProfile(asList(SOME_KEY, INTEGER_VALUE));

        Integer result = profile.getAttribute(SOME_KEY, Integer.class);

        assertEquals(INTEGER_VALUE, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findAttributeStartingWith_shouldThrowExceptionForNullAttributeName() {
        Profile profile = new SimpleProfile(asList(SOME_KEY, INTEGER_VALUE));

        profile.findAttributeStartingWith(null, Object.class);
    }

    @Test
    public void findAttributeStartingWith_shouldReturnNullWhenNoMatchingName() {
        Profile profile = new SimpleProfile(asList(SOME_KEY, INTEGER_VALUE));

        Integer result = profile.findAttributeStartingWith(STARTS_WITH, Integer.class);

        assertNull(result);
    }

    @Test
    public void findAttributeStartingWith_shouldReturnNullForMismatchingType() {
        Profile profile = new SimpleProfile(asList(STARTS_WITH, INTEGER_VALUE));

        String result = profile.findAttributeStartingWith(STARTS_WITH, String.class);

        assertNull(result);
    }

    @Test
    public void findAttributeStartingWith_shouldReturnValueForMatchingKey() {
        Profile profile = new SimpleProfile(asList(STARTS_WITH + ":restOfKey", INTEGER_VALUE));

        Integer result = profile.findAttributeStartingWith(STARTS_WITH, Integer.class);

        assertEquals(INTEGER_VALUE, result);
    }

    @Test
    public void getAttributeSourcesShouldIncludeDrivingLicence() throws ParseException, IOException {
        com.yoti.api.client.spi.remote.proto.AttrProto.Attribute attribute = buildAnchoredAttribute(GIVEN_NAMES_ATTRIBUTE, "A_Given_NAME", DL_ANCHOR);
        
        Profile profile = new SimpleProfile(singletonList(AttributeConverter.convertAttribute(attribute)));
        
        Set<String> sources = profile.getAttributeObject(GIVEN_NAMES_ATTRIBUTE).getSources();
        assertTrue(sources.contains(DRIVING_LICENCE_SOURCE_TYPE));
    }
    
    @Test
    public void getAttributeSourcesShouldIncludePassport() throws ParseException, IOException {
        com.yoti.api.client.spi.remote.proto.AttrProto.Attribute attribute = buildAnchoredAttribute(GIVEN_NAMES_ATTRIBUTE, "A_Given_NAME", PP_ANCHOR);
        
        Profile profile = new SimpleProfile(singletonList(AttributeConverter.convertAttribute(attribute)));
        
        Set<String> sources = profile.getAttributeObject(GIVEN_NAMES_ATTRIBUTE).getSources();
        assertTrue(sources.contains(PASSPORT_SOURCE_TYPE));
    }
    
    @Test
    public void getAttributeVerifiersShouldIncludeYotiAdmin() throws ParseException, IOException {
        com.yoti.api.client.spi.remote.proto.AttrProto.Attribute attribute = buildAnchoredAttribute(GIVEN_NAMES_ATTRIBUTE, "A_Given_NAME", YOTI_ADMIN_ANCHOR);
        
        Profile profile = new SimpleProfile(singletonList(AttributeConverter.convertAttribute(attribute)));
        
        Set<String> sources = profile.getAttributeObject(GIVEN_NAMES_ATTRIBUTE).getVerifiers();
        assertTrue(sources.contains(YOTI_ADMIN_VERIFIER_TYPE));
    }

    private com.yoti.api.client.spi.remote.proto.AttrProto.Attribute buildAnchoredAttribute(String name, String value, String rawAnchor)
            throws InvalidProtocolBufferException {
        Anchor anchor = Anchor.parseFrom(Base64.getDecoder().decode(rawAnchor));
        com.yoti.api.client.spi.remote.proto.AttrProto.Attribute attribute = com.yoti.api.client.spi.remote.proto.AttrProto.Attribute.newBuilder()
                .setName(name)
                .setValue(ByteString.copyFromUtf8(value))
                .addAnchors(anchor)
                .build();
        return attribute;
    }
    
    
    private List<Attribute> asList(String key, Object o) {
        return singletonList(new Attribute(key, o, null));
    }

}
