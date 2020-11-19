package br.com.apidiscount.infra.exception;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ErrorDetail {

    private String title;

    private int status;

    private String detail;

    private long timeStamp;

    private String developerMessage;

}
