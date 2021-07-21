# CatchTheJelly

<h3> General description </h3>

Simple real-strategy 2D Android game. The main task is "chasing" the jelly entity lurking around a rectangular network on a path determined by rules. The "chase" is a simple screen touch on the exact place the entity is, at the right time. Due to the little amount of time the jelly rests on a cell, players must predict the next cell he could trap the jelly into.
The multiple levels and a limited number of failed attempts - "hearts" - develop a nice in-game progress system. Every two levels guarantee an extra "heart" upon completion.
Players can also load a specific level that they want to play, but it requires that all previous levels are unlocked. To unlock a level, they should simply complete it once.

<h3> Short background & gameplay description </h3>

The game follows the story of a little jelly baby. It tries to outrun humans, so that they can't eat it, but eventually you will try your best to chase it. Be careful, there is also a timer base on which the jelly can escape your sights forever! Also, as any other thingies today, this jelly is a powerful AI machine that constantly learns to make his way faster and trickier. Thus, the more you advance level-wise, the more difficult the jelly is to be chased.
You have a number of chances to chase the jelly as it is shown by the number of 'hearts' at the bottom of the screen.

<h3> App implementation </h3>

The app is written in Java language, using the Android Studio platform. It consists of a menu, a game progress window and the gameplay scene.
The gameplay cell network is represented a static array of objects. Depending on the game level, a dynamic array (the entity's path) is spanning over the network based on rules and restrictions. For example, the first level contains a spiral path spanning uniformly over the width and height of the network. Throughout level completion, the next ones become more and more difficult and start to develop the element of random so that the path is increasingly difficult to figure out.
After the level choice, on loading screen the path building rules are selected and a path is generated for the upcoming level.
The app is operated by multiple threads which fire up at spefic time and conditions, and make up the motions of the game. Thus, a Handler().postDelayed() function is performed to a Runnable object (thread). Each thread executes transforms on certain game elements and their execution is interrupted upon completion.
