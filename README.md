# Hack101: Android - Lesson 1 #

### Intro And Setting Up ### 

Hello, and welcome to the first Hack101 of the semester. We will be having 5 Hack101 sessions where we will cover the basics of Android programming. The schedule so far looks like this (subject to change):

* Lesson 1: Setting up/Basics of Android Apps
* Lesson 2: Activities and Activity Lifecycle
* Lesson 3: Saving Data and Preferences
* Lesson 4: UI
* Lesson 5: Web Backends

These tutorials will assume a basic knowledge of Java programming. Additionally, a knowledge of inheritance will be useful later on. If this term is new to you, you can check out this explanation [here](http://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html).

Today's tutorial is on setting up and the basics of Android apps. First things first, we need and IDE to build Android apps in! We will be using Android Studio, the official IDE. You can download Android Studio [here](http://developer.android.com/sdk/index.html). 

If you want to run your apps on your Android phone, you have to turn on USB debugging. Go to Settings > Developer Options and check USB Debugging. If Developer Options does not appear in your settings, you can go to Settings > About Phone and tap the build number seven times. This will turn on developer options. If you don't have an Android phone, do no worry! You can still test your apps on an Android emulator in Android studio. 

### Your First App ###

Today we will be building a simple tip calculator to help us learn about how Android apps work. We begin by opening up Android Studio. Upon opening the program we will be prompted to either start a new project, or import/open an existing project. Let's start a new project. When we click to start a new project we are prompted to enter a project name and a company domain. The company domain is used to unsure that each App has a unique identifier. Since we are not going to be releasing the app the to public, this isn't a huge concern of ours, however. I choose a project name of "Tip Calculator".

![starting an app]()

Next we choose what kind of project we are making. Were making an app for phones, so let's choose "Phone and Tablet" and a minimum SDK of 4.0.3, as android studio recommends. Here we are specifying the minimum operating system we expect our app to be running on. Lower operating systems mean more phones can run it, but this also means that some features of newer Android OS's will not be available to us.

Next we will be prompted to add an activity. We will be covering activities in the next tutorial, so don't worry about it for now. Just choose "Blank Activity" and leave the default settings provided then click finish.


### Structure Of An Android App ###

When we hit finish, Android Studio will take a few moments and will build us a basic 'Hello World' app. Let's explore the structure of the app a bit.

An Android app is a collection of files in a specfic directory structure which Android Studio understands how to compile into a program and your Android OS can run. 

On the left we see a directory navigator. There are two folders in your app's directory. One is called `Gradle Scripts` and this one holds settings and information about your app. You don't need to worry about this folder. The other folder is called `app`. This is where we will be doing all of our work.

Inside the `app` directory, you will see three subdirectories. The first is called `manifests` and contains the file `AndroidManifest.xml`. This is the file where you describe your app to the android system. This file holds the name of the app, the permissions the app will require (like reading SMS, or accessing user identity for example), and more information that the Android system needs to know when it runs your app. We won't need to touch this file for our simple app today, but we will edit this file later when our apps become more complex.

The next folder in the `app` directory is the `java` folder. This folder contains all the logic behind the app. It is here that we will write all of our Java code. 

The final folder is `res`, standing for resources. This folder contains multiple subfolders. 

* The `drawable` folder, which has all the images our app will use. Right now it only has one, the default Android icon.
* The `layout` folder, which holds the files we will use to specify how our app looks to the user.
* The `menu` folder, which holds the files that we use to describe how the menu works. Since we are not making a menu just yet, we don't need to worry about this one.
* The  `values` folder. It is in this folder that we store some important properties of the App, such as strings we will be using in our layout, as well as layout and style settings. 

### Layouts ###

Now that we understand what all these folders do, let's run our app and see what it does! Select the "Run" option at the top of Android studio. If you have an Android phone, connect it to you computer and make sure it is unlocked with USB debugging enabled. A window will pop up asking you how you would like to run your app. You should see your phone as an option here. If you do not have a phone, then select the "Launch Emulator" option. Be warned that the emulator is slow and can also slow down your computer a bit. Here's what you should see!

![hello world]()

You should also see a log pop up at the bottom of your screen. This will let you know what caused a crash if there is one. Also, if you wish to do debugging using `System.out.println()`, this is where you will see the result printed. 

Great, we have a hello world! But how did that all work? Let's have a look at `res/layout/activity_main.xml`. This is the file that specifies what our main activity (the screen a user sees when they open the app) will look like. There are two ways to view this file, Design, and Text (there are tabs at the bottom of the screen which let you switch back and forth). The Design view provides you with a graphical user interface to design your app, while the text view lets you write XML (extensible markup language) to specify your app's appearance (you can think of as being XML is analogous to the HTML in a website). We will be working in the Text view for this tutorial.

![design view]()

![text view]()

Let's break down what we have inside this XML file. In short, XML is a language used to describe how our app will look to users. Notice that everything is wrapped inside `<RelativeLayout ... > ... </RelativeLayout>`. We call this a tag and XML documents are made up entirely by them.  I won't go into too much detail, but for a quick summary you can see [here](http://www.w3schools.com/xml/xml_syntax.asp).

The layout tag is not explicitly visible to the user, it specifies how to layout all of its children. Relative layout means that the children will be displayed in positions relative to each other. The tag the user actually sees in this case is the `TextView` tag. One of the attributes of this tag is the `android:text` attribute. This specifies the text which the text view will display. We see that this attribute has the value `"@string/hello_world". The "@string" at the beginning means that the app shouldn't display the value you've written here, but rather should go look inside your `res/values/strings` file for a string called `hello_world` and display that. If we look inside our `strings` file, lo and behold there is string called `hello_world` and it has the value `Hello World!`. How about we play around with this and make our app a little more personal. I want my app to say hello to me, not the world, so I am going to rename this string to `hello_amiel` and change its value to "Hello, Amiel!".

```xml
    <string name="hello_amiel">Hello Amiel!</string>
```

Let's jump back to `activity_main.xml`. Looks like there's an error now! 

![can't resolve hello world]()

Of course, we changed the name of the value, so our XML file can't find it anymore. This is a quick fix though. replace `@string/hello_world` with `@string/hello_amiel`, and everything should be well again. We can run the app again and see that the text has changed, or on the right of our XML file we should see a little preview which shows us.

### Designing Our App ###

OK, now we are ready to write some XML to make our app look how we want it too! Since we are making a tip calculator, there are three things we need. 

* Somewhere for the user to enter the bill. A Google search tells us that for this we want an `<EditText />` element.
* Somewhere for the user to select the tip amount. Again, Google says we want `<RadioGroup />`. Radio group gives us a group of `<RadioButton />`s which we use when we want a user to select only one of multiple options.
* And finally, "Calculate" button for the user to press. Google says that's a `<Button />` (though maybe we could have guessed that one...).

Let's get rid of that TextView and add an EditText. Android studio will do some autocompleting for us...

![autocomplete]()

I want the text area to fill the screen and I want it to be as high as the text that is going in it, so I will give it the following attributes:

```xml
    <EditText
        android:layout_width="fill_parent"
        android:layout_height="wrap_content" />
```

There's a couple other things I will add. I am going to give it an ID, so that I reference it later. We do this by adding an `android:id` attribute. I am also going to put a bit of space on the top and bottom of the text area, because I can see in the preview that it looks a little wierd when it's flush with the top of the screen. I will also add an input type. Since this is a tip calculator, all I want a user to type in is a decimal number (if they could type in text then that would certainly cause problems). And finally, I add a "hint". This is the grayed out text that appears in the text area when it is empty to prompt the user what to type. After I've added all this, my EditText tag should look like this:

```xml
    <EditText android:id="@+id/bill"
        android:paddingTop="20dp"
        android:paddingBottom="20dp"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:hint="@string/bill_hint"
        android:inputType="numberDecimal"/>
```

All the attributes and their values can be found in the Android documentation with a quick Google. The one curious thing you may have noticed is the plus sign inside the id attribute. The plus sign means I am making a new id when I type this. Another thing to note is that the `dp`s stand for "Density Independent Pixels". They are just a particular unit of length I am using to specify padding around the text area. 

One last thing I have to do is go into my `strings.xml` file and specify what I want the hint to be by adding 

```xml
<string name="bill_hint">Enter your bill here...</string>
```
to the file.


Great! We can now launch the app and see that our text area works!

![edit text works]()

Let's add the radio buttons and the calculate button. 

```xml
    <RadioGroup android:id="@+id/tip_radio"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:layout_below="@id/bill">

        <RadioButton android:id="@+id/tip_radio_low"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/tip_low"
            android:checked="true"/>

        <RadioButton android:id="@+id/tip_radio_mid"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/tip_mid"/>

        <RadioButton android:id="@+id/tip_radio_high"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/tip_high"/>
    </RadioGroup>
    
    <Button android:id="@+id/calculate_button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/calculate"
        android:layout_below="@id/tip_radio"
        android:layout_alignParentRight="true"/> 
```

The outer wrapper RadioGroup groups our buttons together, and each button is a new RadioButton tag. A couple things to note here. One is that the RadioGroup has a `android:layout_below` attribute. Since we are inside a RelativeLayout tag, we can position elements relative to each other. Here I am saying that I want the radio buttons to appear below the text input area (which I gave an ID `bill`). Also notice that I am giving each element an ID. This is important because I may need to reference them later. Let me specify all the strings I've used here now by adding this to the `strings` file.

```xml
    <string name="calculate">Calculate Your Tip</string>
    <string name="tip_low">13%</string>
    <string name="tip_mid">15%</string>
    <string name="tip_high">18%</string>
```

If we run our app, we see that everything looks good! 

![main page looks good]()

But our app only looks nice. It is still dumb. Pressing the button does nothing. This is where the Java comes in.

### Adding Logic To Our App ###

We now turn our attention to the `MainActivity` class. Here is where we will be writing all of our code and this is where all the logic takes place. Inside `MainActivity.java` there is a bunch of code that Android Studio generated for us. We don't have to worry about it for now, it will all be explained in the next lesson. Now all we have to do is write a method which will take the tip a user types in and calculate, then display this tip to the user. We add a `calculateTip` method to our class. This method accepts a `View` as it's argument. A view is a component of a layout. 

```java
    public void calculateTip(View view){

    }
```

This will give us an error that we cannot resolve View, so we have to import it. If we click on it and press option-control on OSX (I'm not sure what it is for windows, but there should a prompt that comes up and suggests the shortcut to you), Android Studio will automatically import anything you are missing (this is a quick fix to a very common bug).

Here's what our method will look like. It is explained below.


```java
    public void calculateTip(View view){

        // find the input area and get its contents as a string
        EditText input =  (EditText) findViewById(R.id.bill);
        String inputBill = input.getText().toString();

        // if the area is empty, the display a message and return
        if (inputBill.matches("")) {
           Toast.makeText(this, "You did not enter a bill amount", Toast.LENGTH_SHORT).show();
           return;
        }

        // the input is not empty, so we cast the string to a float
        float bill = Float.valueOf(inputBill);

        // get the radio button objects to check if they are selected
        RadioButton low = (RadioButton) findViewById(R.id.tip_radio_low);
        RadioButton mid = (RadioButton) findViewById(R.id.tip_radio_mid);

        // declare our tip variable
        float tip;

        // calculate the top according to button is selected
        if (low.isChecked())
            tip = bill * 13 / 100;
        else if (mid.isChecked())
            tip = bill * 15 / 100;
        else
            tip = bill * 18 / 100;

        //TODO: Display our tip to the used
    }
```

The one thing which may seem unusual to you here is the `Toast` object. This is used to show a quick message to the user. Another thing to note is the `R` object. This is a big class which has all of your resources in it, so it's from here we can find views in our layout. 

### Linking Our Logic And Our Display ###

We still need to display our tip to the user. We can do this by adding an empty text view to our main layout and then filing that text view with the tip once we have calculated it. Let's add a text view below our calculate tip button inside `activity_main.xml`:

```xml
    <TextView android:id="@+id/display_tip"
        android:layout_below="@id/calculate_button"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:paddingTop="30dp"
        android:gravity="center"
        android:textSize="20sp"
        />
```

Besides the usual ID and formatting, there is also an attribute called `android:gravity`. Here I am specifying that I want to center the text inside the text view. By making the text view fill the parent and also centering the text, the text will be centered in the screen. Also note that I am specifying the text size in `sp` (scale independent pixels) and not `dp`. This is the required unit of size for text. All the attributes I use are in the Android documentation and can be found with a quick Google search.

Now to add this view to our `calculateTip` method.

```java
    public void calculateTip(View view){

        // find the input area and get its contents as a string
        EditText input =  (EditText) findViewById(R.id.bill);
        String inputBill = input.getText().toString();

        // if the area is empty, the display a message and return
        if (inputBill.matches("")) {
           Toast.makeText(this, "You did not enter a bill amount", Toast.LENGTH_SHORT).show();
           return;
        }

        // the input is not empty, so we cast the string to a float
        float bill = Float.valueOf(inputBill);

        // get the radio button objects to check if they are selected
        RadioButton low = (RadioButton) findViewById(R.id.tip_radio_low);
        RadioButton mid = (RadioButton) findViewById(R.id.tip_radio_mid);

        // declare our tip variable
        float tip;

        // calculate the top according to button is selected
        if (low.isChecked())
            tip = bill * 13 / 100;
        else if (mid.isChecked())
            tip = bill * 15 / 100;
        else
            tip = bill * 18 / 100;

        // get the text view we are going to fill with the tip
        TextView tipView = (TextView) findViewById(R.id.display_tip);

        // set it to empty (in case this is the second time someone is calculating a tip
        // we want to make sure it is empty)
        tipView.setText(String.format(""));

        // fill it with the tip we calculated
        tipView.setText(String.format("Your tip will be $%.2f.", tip)); 
    }
```

OK, we're almost done! The one problem is that our method is never called! The "Calculate" Button does nothing! Well this is an easy fix, just tell the calculate button to call this method whenever it is clicked. (Actually, when we say that `calculateTip` takes a `View` parameter, it is taking the view which called it. So our method takes the calculate button as an argument, but we didn't need it in the end). We add the following line to our `Button` tag in `activity_main.xml`: `android:onClick="calculateTip"`. This tells the calculate tip button to go ahead an call our method when it is clicked. Our button should look like this now:

```xml
    <Button android:id="@+id/calculate_button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/calculate"
        android:layout_below="@id/tip_radio"
        android:layout_alignParentRight="true"
        android:onClick="calculateTip"/>
```

And voila! We have our first working app!

![successful app running]()

