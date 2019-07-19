package com.gdt.models.beans;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FAQsDto {

    private String id;
    private String code;
    private String answer;
    private String question;
    private String link;

    @Override
    public String toString() {
        return "FAQsDto{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", answer='" + answer + '\'' +
                ", question='" + question + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
