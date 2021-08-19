package myproject.game.services.impl;

import myproject.game.dao.QuestionRepository;
import myproject.game.mappers.QuestionMapper;
import myproject.game.models.LoginWrapper;
import myproject.game.models.dto.QuestionDto;
import myproject.game.models.dto.SessionDto;
import myproject.game.models.entities.Question;
import myproject.game.services.QuestionService;
import myproject.game.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private SessionService sessionService;


    @Override
    public QuestionDto saveQuestion(String auth, QuestionDto questionDto) {
        SessionDto sessionDto = sessionService.getSessionInfo(auth);
        if(!sessionDto.getUser().isAdmin()){
            throw new RuntimeException("Not Allowed!!!");
        }
        Question question = QuestionMapper.INSTANCE.qusetionDtoToQuestion(questionDto);
        question = questionRepository.save(question);
        return QuestionMapper.INSTANCE.questionToQuestionDto(question);
    }

    @Override
    public List<QuestionDto> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return QuestionMapper.INSTANCE.questnsToQuestnDtos(questions);
    }

    @Override
    public QuestionDto getRandomQuestionFromList() {
        List<Question> questions = questionRepository.findAll();
        List<QuestionDto> list = QuestionMapper.INSTANCE.questnsToQuestnDtos(questions);
            QuestionDto chosen_question;
            int num_words = list.size();
            Random r = new Random(System.currentTimeMillis());
            int ran_int = r.nextInt(num_words);
            chosen_question = list.get(ran_int);
            return chosen_question;
    }
}
