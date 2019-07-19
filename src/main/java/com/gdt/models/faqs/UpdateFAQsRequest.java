package com.gdt.models.faqs;

import com.gdt.models.beans.FAQsDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFAQsRequest {

    private String id;
    private String code;
    private String answer;
    private String question;
    private String link;


    public UpdateFAQsRequest(FAQsDto faQsDto) {
        this.id = faQsDto.getId();
        this.code = faQsDto.getCode();
        this.answer = faQsDto.getAnswer();
        this.question = faQsDto.getQuestion();
        this.link = faQsDto.getLink();
    }

}
