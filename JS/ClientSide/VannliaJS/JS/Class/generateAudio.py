from gtts import gTTS as googleTextToSpeech
import os

lang = 'en'
os.makedirs(lang, exist_ok=True)

# for i, j in words: print(f"{i} -> {j}.mp3"), googleTextToSpeech(text=i, lang=lang).save(f"{lang}/{j}.mp3")

text = input("Text >> ")
googleTextToSpeech(text=text, lang="en").save(input("Filename >> ")+".mp3")
