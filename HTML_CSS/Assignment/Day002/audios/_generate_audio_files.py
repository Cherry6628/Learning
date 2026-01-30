from gtts import gTTS as googleTextToSpeech
import os

lang, words = "ja", [
    ("こんにちは", "konnichiwa"),
    ("ありがとう", "arigatou"),
    ("さようなら", "sayounara"),
    ("おはよう", "ohayou"),
    ("こんばんは", "konbanwa"),
    ("すみません", "sumimasen"),
    ("はい", "hai"),
    ("いいえ", "iie"),
    ("わかりません", "wakarimasen"),
    ("トイレ", "toire")
]
# lang = "te"
# words = [
#     ("కల", "dream"),
#     ("స్నేహం", "friendship"),
#     ("నమ్మకం", "trust"),
#     ("త్యాగం", "sacrifice"),
#     ("ధైర్యం", "courage"),
#     ("నయం", "kindness"),
#     ("జీవితం", "life"),
#     ("జ్ఞానం", "knowledge"),
#     ("వెలుగు", "light"),
#     ("విజయము", "victory")
# ]
os.makedirs(lang, exist_ok=True)

# for i, j in words: print(f"{i} -> {j}.mp3"), googleTextToSpeech(text=i, lang=lang).save(f"{lang}/{j}.mp3")

text = """
The Importance of Learning Web Development...

A modern skill for a digital age...
Introduction.

In today's digital world, learning web development has become an essential skill for students, professionals, and entrepreneurs alike. From personal websites to large-scale business applications, the web is at the heart of communication and commerce.
What is Web Development?

Web development involves creating websites and applications using technologies like HTML, CSS, and JavaScript. While HTML provides structure, CSS handles presentation, and JavaScript brings interactivity.
.
.
.
.
.

    "Any fool can write code that a computer can understand. Good programmers write code that humans can understand." by Martin Fowler 

Why Should You Learn It?

    High Demand: Web developers are in great demand across industries.
    Creative Expression: Build portfolios, blogs, and projects that reflect your identity.
    Problem-Solving: Programming sharpens logical thinking and planning skills.
    Career Flexibility: Freelancing, remote jobs, or starting your own company becomes possible.

How to Get Started

Begin with learning HTML and CSS. Start small: build a personal homepage. Then move on to JavaScript. Practice regularly. Participate in online communities. Most importantly, build real projects that excite you.


Conclusion

Learning web development is more than writing code. It's about building the future, communicating ideas, and solving problems. Whether you aim to be a developer or simply want to understand how the web works, these skills are incredibly valuable in the 21st century.

"""

googleTextToSpeech(text=text, lang="en").save("audio.mp3")