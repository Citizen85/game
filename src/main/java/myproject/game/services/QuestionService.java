package myproject.game.services;

import myproject.game.models.dto.QuestionDto;

import java.util.List;

public interface QuestionService {
    QuestionDto saveQuestion(String auth, QuestionDto questionDto);

    List<QuestionDto> getAllQuestions();
    QuestionDto getRandomQuestionFromList();
}
