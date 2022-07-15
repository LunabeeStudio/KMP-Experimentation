# The journey of Lunabee Studio with KMM

At Lunabee Studio, we love building native apps, but what we love most of all is building great apps. But the main issue with doing native apps is that some logic that don’t feel platform specific needs to be done twice, increasing the odds of having differences and also the development time.

To keep doing our lovely jobs and improve our working methods, we watch the Android and iOS sphere to be up-to-date on the latest technologies, best practices, etc… Thus, we couldn’t miss the emergence of Kotlin Multiplatform Mobile.

We already said that [hybrid apps are crap](https://medium.com/lunabee-studio/why-hybrid-apps-are-crap-6f827a42f549), but KMM philosophy could suit us more, as it leaves the UI part out of the scope and promises performances as good as native apps.

## 🥅 Goal

The main goal of this project is to answer one question : can it help us delivery apps faster without degrading the development time and premium quality of our application?

To answer this big question, we asked ourselves smaller questions that could help us have a better understanding on the state of KMM. Questions such as :

-   What are the advantages and disadvantages of KMM?
-   Is it ready for production?
-   If not, what is missing? What do we need to keep a watch on before we can start using it?
-   How much of our code base can we share between platforms?

## 🧭 Roadmap

To reach our goal and evaluate independently which module could be replaced using KMM, we thought of small projects to test them step by step before doing the main project.

1.  **The most simple project to use KMM, a lib with a function to check a string in it**
2.  [A project to replace the server calls](https://github.com/LunabeeStudio/KMP-Experimentation/tree/network/kmm)
3.  [The third project aim to see if we could replace the local database](https://github.com/LunabeeStudio/KMP-Experimentation/tree/local/kmm)
4.  [Finally, a real app to combine all 3 first projects](https://github.com/LunabeeStudio/KMP-Experimentation/tree/complete_app/kmm)