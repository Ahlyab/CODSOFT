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
    
    
        