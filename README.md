# POO TV
> Boiangiu Victor-Miron, 325 CD
## How the code is organized

The code is organized in the following main classes:
- StreamingPlatform: contains the Users and Movies database, runs the actions given in the input and handles output;
- Database: pretty much just a wrapper for an ArrayList, containing objects of a given class;
- User: contains all information about a specific user: his name, password, tokens, etc.
- Page: the template for all pages, contains the names of pages that can be accessed from it and actions that can be run in it.
- Command: the template for other commands that contain the logic for a specific action: filter, watch, etc.
- Movie: contains details of a specific movie: its title, its filming year, etc.

## The workflow

The input JSON file is read by the Main method using Jackson (props to the first homework for this!) and turned into Input objects, that are then passed to the StreamingPlatform instance.

The StreamingPlatform instance then resets all of its data (since Singleton breaks tests that are run one after the other), then initializes its Users and Movies databases from the input it received and turns all actions into Commands using the Factory design pattern, inserting them into the CommandParser to be ready for execution.

Once everything is set, CommandParser is told to execute all Commands, going through them one by one and executing them.

## Design patterns used

The following design patterns were used:
- Singleton: for the StreamingPlatform class, since only one object's existence made sense, and it also meant easy access to everything in it from all other classes;
- Factory: used for pages and commands, most useful for pages, since it's used everytime a new page is required;
- Command: used for the logic for each action.

## The ideas behind some implementation decisions

The StreamingPlatform class uses Singleton because it is the "brain" of the app, it contains all data, and it made sense that only one instance exists, also making access to said data quick and easy for all classes (especially useful in Commands).

All pages are their own class because, once the initial project structure was made, expanding the platform to have multiple pages was very easy: all you have to do is say which pages are accessible from the new one, what actions can be done on it, and add it to the Page factory.

## Final thoughts

I had a lot of fun with this one and the experience I gathered from the first homework really showed. You've done a good job!

---
### Mao Tse-tung says:
> Nice homework!

<img src="nice_homework.jpg" width="200">