from Question import Question

class Quiz:
    questions = []
    currentAnswer = ""

    def __init__(self) -> None:
        pass

    def addQuestion(self, question : Question) -> None:
        self.questions.append(question)

    def getQuestion(self) -> Question:
        q = self.questions.pop(0)
        return q
    def addDummyQuestions(self):
        self.addQuestion(Question("What is the output of 'print(3 * 4)'?", ["12", "7", "0", "34"], 0))

        self.addQuestion(Question("Which data structure is typically used for implementing a LIFO behavior?", ["Queue", "Array", "Stack", "Linked List"], 2))

        self.addQuestion(Question("In Python, what does the 'len()' function do?", ["Returns the largest element in a list", "Returns the number of items in an object", "Returns the sum of all elements in a list", "Returns the smallest element in a list"], 1))

        self.addQuestion(Question("What does the acronym 'SQL' stand for?", ["Structured Query Language", "Standard Query Language", "Sequential Query Language", "Structured Question Language"], 0))

        self.addQuestion(Question("Which programming language is known for its use in web development and is often referred to as 'the language of the web'?", ["Python", "Java", "Ruby", "JavaScript"], 3))

        self.addQuestion(Question("What is the purpose of the 'try...except' block in Python?", ["To define a function", "To declare a variable", "To handle exceptions and errors", "To create a loop"], 2))

        self.addQuestion(Question("What does the 'git' version control system primarily help with?", ["Database management", "Code execution", "Project collaboration", "Source code versioning"], 3))

        self.addQuestion(Question("Which of the following is not a primitive data type in Python?", ["int", "float", "boolean", "string"], 2))

        self.addQuestion(Question("What does the acronym 'API' stand for in software development?", ["Application Programming Interface", "Advanced Programming Interface", "Automated Program Integration", "Application Process Integration"], 0))

        self.addQuestion(Question("In object-oriented programming, what is the term used to describe a blueprint for creating objects?", ["Plan", "Recipe", "Class", "Template"], 2))
    

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


