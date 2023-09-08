from PyQt5.QtCore import *
from PyQt5.QtWidgets import *
from PyQt5.uic import loadUiType
import sys
from PyQt5.QtGui import *
from Question import Question
from Quiz import *


ui, _ = loadUiType("quiz_app.ui")

class MainApp(QMainWindow, ui):
        quiz = Quiz()
        feedback = FeedBack()
        def __init__(self, parent=None):
            super(MainApp, self).__init__(parent)
            QMainWindow.__init__(self)
            self.setupUi(self)
            self.quiz.addDummyQuestions()
            self.addQuestion()

        def addQuestion(self):
            q = self.quiz.getQuestion()
            self.question.setText(q.getQuestionStatement())
            options = q.getOptions()
            self.option1.setText(options[0])
            self.option2.setText(options[1])
            self.option3.setText(options[2])
            self.option4.setText(options[3])
            


def main():
    app = QApplication(sys.argv)
    windows = MainApp()
    windows.show()
    app.exec_()
if __name__ == "__main__":
    main()