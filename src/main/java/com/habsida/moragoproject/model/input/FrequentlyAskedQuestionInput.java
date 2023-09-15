package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.enums.FAQCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FrequentlyAskedQuestionInput {

    private FAQCategory category;
    private String answer;
    private String question;
}
