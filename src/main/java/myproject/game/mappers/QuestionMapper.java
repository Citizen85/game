package myproject.game.mappers;

import myproject.game.models.dto.QuestionDto;
import myproject.game.models.entities.Question;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    Question qusetionDtoToQuestion(QuestionDto questionDto);
    QuestionDto questionToQuestionDto(Question question);

    List<Question> questnDtosToQuestns(List<QuestionDto> questionDtos);
    List<QuestionDto> questnsToQuestnDtos(List<Question> questions);
}
