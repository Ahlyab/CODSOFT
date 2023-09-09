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
        current_question = None
        def __init__(self, parent=None):
            super(MainApp, self).__init__(parent)
            QMainWindow.__init__(self)
            self.setupUi(self)
            self.quiz.addDummyQuestions()
            self.addQuestion()
            self.submit.clicked.connect(self.btnClicked)

        def addQuestion(self):
            self.option1.setAutoExclusive(False)

            self.option1.setChecked(False)

            self.option2.setAutoExclusive(False)
            self.option2.setChecked(False)

            self.option3.setAutoExclusive(False)
            self.option3.setChecked(False)

            self.option4.setAutoExclusive(False)
            self.option4.setChecked(False)


            self.option1.setAutoExclusive(True)
            self.option2.setAutoExclusive(True)
            self.option3.setAutoExclusive(True)
            self.option4.setAutoExclusive(True)

            self.current_question = self.quiz.getQuestion()
            self.question.setText(self.current_question.getQuestionStatement())
            options = self.current_question.getOptions()
            self.option1.setText(options[0])
            self.option2.setText(options[1])
            self.option3.setText(options[2])
            self.option4.setText(options[3])

        def btnClicked(self):
            ans = ""
            if(self.option1.isChecked()):
                 ans = self.option1.text()
            elif (self.option2.isChecked()):
                 ans = self.option2.text()
            elif (self.option3.isChecked()):
                 ans = self.option3.text()
            elif (self.option4.isChecked()):
                 ans = self.option4.text()
            elif (ans == ""):
                dlg = QMessageBox(self)
                dlg.setWindowTitle("Error!")
                dlg.setText("Please at least one option")
                dlg.exec()
                return

            self.feedback.checkAnswer(self.current_question, ans )
            ans = ""

            if(self.quiz.questions.__len__() == 0):
                dlg = QMessageBox(self)
                dlg.setWindowTitle("Feedback")
                dlg.setText(self.feedback.generateReport())
                dlg.exec()
                QApplication.quit()
                return

            self.addQuestion()
            


def main():
    app = QApplication(sys.argv)
    windows = MainApp()
    windows.show()
    app.exec_()
if __name__ == "__main__":
    main()