# // python class to implement Question that has a statement, 4 options and index of correct answer

class AnswerOutOfBoundException(Exception):
    pass

class Question:
    def __init__(self, question : str, options : list, correctAnswerIndex : int ):
        self.question = question
        self.options = options
        if(correctAnswerIndex < 0 or correctAnswerIndex >= len(options)):
            raise AnswerOutOfBoundException
        else:
            self.correctAnswerIndex = correctAnswerIndex

    def getCorrectAnswer(self) -> str:
        return self.options[self.correctAnswerIndex]
        
    def getOptions(self) -> list[str]:
        return self.options
        
    def getQuestionStatement(self) -> str:
        return self.question
        
    def getOptionsSize(self) -> int:
        return self.options.__len__() 
        

