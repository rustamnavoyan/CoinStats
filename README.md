    CoinStats app for showing first 100 coin list in one tab and favorite coin list in another tab

    The following issues/improvements are not done due to lack of time

    Functional issues:
        1. Sometimes the list is not shown when run from IDEA (Could not reproduce in normal testing)
        2. Having no message or feedback when tapping on items is bad from UX point of view
        3. The design (toolbar etc.)

    Necessary improvements:
        1. Create dependencies.gradle file
        2. Add icon placeholder
        3. Sometimes domain, data, presentation and ui are mixed in clean architecture they should be separate but probably not necessary for such a small app
        4. This is the very first time I used Realm db and I am not aware of any best practises therefore I noticed it does not work very well with DI
        5. Create BaseViewModel (and probably it will help to eliminate some code duplication)

