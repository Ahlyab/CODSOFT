from PyQt5.QtCore import *
from PyQt5.QtWidgets import *
import sys
from PyQt5.QtWidgets import QAction
from PyQt5.uic import loadUiType

ui, _ = loadUiType("calculator.ui")

class MainApp(QMainWindow, ui):
    _first = None
    _second = None
    _isOperationSelected = None
    _operation = ""
    _eq = ""
    _decimalClicked = False

    def __init__(self, parent=None):
        super(MainApp, self).__init__(parent)
        QMainWindow.__init__(self)

        self.setupUi(self)
        self.handleEvents()
        

    def handleEvents(self):
        self.output.setDecMode()
        self.btn1.clicked.connect(self.btn1Clicked)
        self.btn2.clicked.connect(self.btn2Clicked)
        self.btn3.clicked.connect(self.btn3Clicked)
        self.btn4.clicked.connect(self.btn4Clicked)
        self.btn5.clicked.connect(self.btn5Clicked)
        self.btn6.clicked.connect(self.btn6Clicked)
        self.btn7.clicked.connect(self.btn7Clicked)
        self.btn8.clicked.connect(self.btn8Clicked)
        self.btn9.clicked.connect(self.btn9Clicked)
        self.btn0.clicked.connect(self.btn0Clicked)
        self.btnDecimal.clicked.connect(self.decimalClicked)
        self.plus.clicked.connect(self.plusf)
        self.minus.clicked.connect(self.minusf)
        self.divide.clicked.connect(self.dividef)
        self.multiply.clicked.connect(self.multiplyf)
        self.equal.clicked.connect(self.equalf)

    def btn1Clicked(self):
        self._eq = self._eq + self.btn1.text()
        self.output.display(self._eq)

    def btn2Clicked(self):
        self._eq = self._eq + self.btn2.text()
        self.output.display(self._eq)
    
    def btn3Clicked(self):
        self._eq = self._eq + self.btn3.text()
        self.output.display(self._eq)
    
    def btn4Clicked(self):
        self._eq = self._eq + self.btn4.text()
        self.output.display(self._eq)
    
    def btn5Clicked(self):
        self._eq = self._eq + self.btn5.text()
        self.output.display(self._eq)
    
    def btn6Clicked(self):
        self._eq = self._eq + self.btn6.text()
        self.output.display(self._eq)

    def btn7Clicked(self):
        self._eq = self._eq + self.btn7.text()
        self.output.display(self._eq)  

    def btn8Clicked(self):
        self._eq = self._eq + self.btn8.text()
        self.output.display(self._eq)

    def btn9Clicked(self):
        self._eq = self._eq + self.btn9.text()
        self.output.display(self._eq)

    def btn0Clicked(self):
        self._eq = self._eq + self.btn0.text()
        self.output.display(self._eq)

    def decimalClicked(self):
        if not self._decimalClicked:
            self._eq = self._eq + self.btnDecimal.text()
            self.output.display(self._eq)
            self._decimalClicked = True  


    def plusf(self):
        self.operationClicked()
        self._operation = "+"
    
    def minusf(self):
        self.operationClicked()
        self._operation = "-"
    
    def multiplyf(self):
        self.operationClicked()
        self._operation = "x"
    
    def dividef(self):
        self.operationClicked()
        self._operation = "/"
    


    def operationClicked(self):
        self._first = float(self._eq)
        self.output.display("")
        self._isOperationSelected = True
        self.disableOperations(True)
        self._eq = ""

    
    def disableOperations(self, flag : bool):
        self.plus.setDisabled(flag)
        self.minus.setDisabled(flag)
        self.divide.setDisabled(flag)
        self.multiply.setDisabled(flag)

    def equalf(self):
        self.output.display("")
        self._second = float(self._eq)
        if self._operation == "+":
            self._first = (self._first + self._second)
        elif self._operation == "-":
            self._first = (self._first - self._second)
        elif self._operation == "x":
            self._first = (self._first * self._second)
        elif self._operation == "/":
            self._first = (self._first / self._second)
        self.disableOperations(False)
        self.output.display(self._first)
        self._eq = str(self._first)
        


def main():
    app = QApplication(sys.argv)
    windows = MainApp()
    windows.show()
    app.exec_()
if __name__ == "__main__":
    main()

