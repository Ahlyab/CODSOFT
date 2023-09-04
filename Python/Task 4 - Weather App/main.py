from PyQt5.QtCore import *
from PyQt5.QtWidgets import *
import sys
from PyQt5.QtWidgets import QAction
from PyQt5.uic import loadUiType
from PyQt5.QtGui import *
from weather import *
import os
from dotenv import load_dotenv

load_dotenv()



ui, _ = loadUiType("weather_app.ui")

class MainApp(QMainWindow, ui):
        _api = os.getenv("API")
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