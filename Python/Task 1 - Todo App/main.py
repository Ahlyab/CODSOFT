from PyQt5.QtCore import *
from PyQt5.QtWidgets import *
import sys
from PyQt5.QtWidgets import QAction
from PyQt5.uic import loadUiType

ui, _ = loadUiType("todo.ui")


class MainApp(QMainWindow, ui):
    itemSelected = None

    def __init__(self, parent=None):
        super(MainApp, self).__init__(parent)
        QMainWindow.__init__(self)
        self.setupUi(self)
        self.settingUpUI()
        
    def settingUpUI(self):
        self.addBtn.clicked.connect(self.addButtonCLicked)
        self.list.itemDoubleClicked.connect(self.removeClickedItem)
        self.list.itemClicked.connect(self.itemSelect)
        self.input.returnPressed.connect(self.addOnEnter)
        self.deleteBtn.setDisabled(True)
        self.updateBtn.setDisabled(True)
        self.deleteBtn.clicked.connect(self.deleteBtnClicked)
        self.updateBtn.clicked.connect(self.updateTask)


    def addButtonCLicked(self):
        text = str(self.input.text())
        if len(text) > 0:
            self.list.addItem(text)
        self.input.setText("")

    def removeClickedItem(self, item):
        index = self.list.row(item)
        self.list.takeItem(index)

    def addOnEnter(self):
        self.addButtonCLicked()

    def itemSelect(self, item):
        self.enableUpdateAndDeleteButton(True)
        self.itemSelected = item
        self.input.setPlaceholderText("Enter updated task")

    def deleteBtnClicked(self):
        self.removeClickedItem(self.itemSelected)
        self.enableUpdateAndDeleteButton(False)


    def updateTask(self):
        self.itemSelected.setText(self.input.text())
        self.input.setText("")
        self.enableUpdateAndDeleteButton(False)

    def enableUpdateAndDeleteButton(self, flag: bool):
        self.deleteBtn.setEnabled(flag)
        self.updateBtn.setEnabled(flag)


def main():
    app = QApplication(sys.argv)
    windows = MainApp()
    windows.show()
    app.exec_()
if __name__ == "__main__":
    main()

