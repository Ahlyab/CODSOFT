import random

class Password_gen:
    lower = "abcdefghijklmnopqrstuvwxyz"
    upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    numbers = "0123456789"
    symbols = "@#$&_-()=%*:/!?+."

    all_chars = ""

    def __init__(self, size) -> None:
        self.size = size
        self.all_chars = self.lower + self.upper + self.numbers + self.symbols
    
    def generate_password(self):
        return "".join(random.sample(self.all_chars, self.size))
