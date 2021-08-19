package myproject.game.models.dto;

public class QuestionDto {
    private Long id;
    private String questn;
    private String answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestn() {
        return questn;
    }

    public void setQuestn(String questn) {
        this.questn = questn;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
