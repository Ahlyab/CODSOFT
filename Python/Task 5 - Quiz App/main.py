from PyQt5.QtCore import *
from PyQt5.QtWidgets import *
from PyQt5.uic import loadUiType
import sys
from PyQt5.QtGui import *
from Question import Question
from Quiz import *


ui, _ = loadUiType("quiz_app.ui")

class MainApp(QMainWindow, ui):
        def __init__(self, parent=None):
            super(MainApp, self).__init__(parent)
            QMainWindow.__init__(self)

            self.setupUi(self)


def main():
    app = QApplication(sys.argv)
    windows = MainApp()
    windows.show()
    app.exec_()
if __name__ == "__main__":
    main()