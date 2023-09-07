from Question import Question

class Quiz:
    questions = []
    currentAnswer = ""

    def __init__(self) -> None:
        pass

    def addQuestion(self, question : Question) -> None:
        self.questions.append(question)

    def getQuestion(self):
        q = self.questions.pop(0)
        return q
    

class FeedBack:
    score = 0
    QA = None
    def __init__(self) -> None:
        pass

    def checkAnswer(self, question : Question, answer : str):
        if(question.getCorrectAnswer() == answer):
            self.score = self.score + 1
        self.QA[question.getStatement()] = question.getCorrectAnswer()

    def generateReport(self) -> str:
        report = f"your score is {self.score} \n"
        for question, answer in self.QA.items:
            report = report + f"Q: {question} \n A: {answer} \n"
        return report
