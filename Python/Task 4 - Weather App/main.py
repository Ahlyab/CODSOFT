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

        def buttonclicked(self):
            input = self.input.text()
            data = get_weather_data(self._api, input)
            report = get_weather_report(data)

        def updateFields(self, report):
            # update fields according to report data
            pass

def main():
    app = QApplication(sys.argv)
    windows = MainApp()
    windows.show()
    app.exec_()
if __name__ == "__main__":
    main()