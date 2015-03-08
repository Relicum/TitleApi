##TitleApi 1.0.9-SNAPSHOT

**Built using the best Java IDE around**

[![Image of JetBrains](https://www.jetbrains.com/idea/docs/logo_intellij_idea.png)](https://www.jetbrains.com/idea/)

```
REQUIRES
* Latest Spigot 1.8.3
* Java 8 installed (Server Side)
```

TitleApi gives developers easy access to the following (MC)1.8 features:

* Title
* SubTitle
* Tab Header
* Tab Footer
* Action Bar

---

All plugins that wish to use this must make sure there plugin.yml includes a dependency to TitleApi

###Get an instance of the API
```java
       TitleMaker api;
       if (!getServer().getPluginManager().isPluginEnabled("TitleApi")) {
           getServer().getPluginManager().disablePlugin(this);
       } else {
           try {
               api = ((TitleApi) getServer().getPluginManager().getPlugin("TitleApi")).getTitleApi(this);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
```

The API object has methods to create all other objects. Simple Titles Example:

```java
            api.getNewBasicTitle()
                    .withFadeIn(2)
                    .withStay(100)
                    .withFadeOut(2)
                    .withTitle("&4This is the &7MAIN title")
                    .withSubTitle("&bThis is the sub title")
                    .withReset()
                    .getTitleObject()
                    .send(player);
```
Simple ActionBar example:

```java
api.sendActionBar(player,"&5Display a message on the action bar");
```

More advanced examples to follow.




