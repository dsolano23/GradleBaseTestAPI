package com.gdt.models.faqs;

import com.gdt.models.beans.FAQsDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFAQsRequest {

    private String code;
    private String answer;
    private String question;
    private String link;


    public CreateFAQsRequest(FAQsDto faQsDto) {
        this.code = faQsDto.getCode();
        this.answer = faQsDto.getAnswer();
        this.question = faQsDto.getQuestion();
        this.link = faQsDto.getLink();
    }
/*
    @Override
    public String toString() {
        return "{" + "\n\t\t" +
                "code='" + code + "\n\t\t" +
                ", answer='" + answer + "\n\t\t" +
                ", question='" + question + "\n\t\t" +
                ", link='" + link + "\n\t" +
                '}';
    }*/
}
