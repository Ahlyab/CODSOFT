from PyQt5.QtCore import *
from PyQt5.QtWidgets import *
import sys
from PyQt5.QtWidgets import QAction
from PyQt5.uic import loadUiType
from PyQt5.QtGui import *
from password_gen import Password_gen


ui, _ = loadUiType("password_generator.ui")

class MainApp(QMainWindow, ui):
        def __init__(self, parent=None):
            super(MainApp, self).__init__(parent)
            QMainWindow.__init__(self)

            self.setupUi(self)
            self.output.setDisabled(True)
            self.copy.setDisabled(True)
            self.generate.clicked.connect(self.passGen)
            self.copy.clicked.connect(self.copyToClipboard)

        def passGen(self):
            passwordLength = int(self.selector.text())
            generator = Password_gen(passwordLength)
            self.output.setDisabled(False)
            self.output.setText(generator.generate_password())
            self.copy.setDisabled(False)
            self.copy.setText("copy to clipboard")

        def copyToClipboard(self):
            password = self.output.text()
            clipboard = QApplication.clipboard()
            clipboard.setText(password)
            self.copy.setText("Copied!")
            
             

        


def main():
    app = QApplication(sys.argv)
    windows = MainApp()
    windows.show()
    app.exec_()
if __name__ == "__main__":
    main()