# User Guide

Automated Insurance Assistant (AIA) is **a desktop app for managing contacts, optimised for use via a Command Line
Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AIA can
handle all your contact management processes, making sure you always have a snapshot of your clients’ profiles on hand.

----

## Table of Contents

* [Quick Start](#quick-start)
* [Features](#features)
  * [Adding a contact](#adding-a-contact-add)
  * [Listing all contacts](#listing-all-contacts--list)
  * [Editing a contact](#editing-a-contact--edit)
  * [Finding contacts by name](#finding-contacts-by-name-find)
  * [Deleting a contact](#deleting-a-contact--delete)
  * [Assigning dates to contact](#assigning-dates-to-a-contact-contacted)
  * [Displaying contacted contacts within DAYS](#displaying-contacted-contacts-within-days-within)
  * [Displaying contacted contacts after DAYS](#displaying-contacted-contacts-later-than-days-later)
  * [Adding a non-recurring reminder to a contact](#adding-a-non-recurring-reminder-to-a-contact-remind)
  * [Viewing reminders of a contact](#viewing-reminders-of-a-contact-reminder)
  * [Viewing contacts by reminder](#viewing-contacts-by-reminder-reminders)
  * [Deleting a reminder](#deleting-a-reminder-forget)
  * [Adding a tag](#adding-a-tag-tag)
  * [Deleting a tag](#deleting-a-tag-untag)
  * [Showing all available tags](#showing-all-available-tags-tags)
  * [Find contacts with tag](#finding-contacts-with-tag-)
  * [Exiting the program](#exiting-the-program--exit)
  * [Saving the date](#saving-the-data)
  * [Adding automatic recurring reminders](#adding-automatic-recurring-reminders-coming-in-v13)
* [Interesting Features](#interesting-features)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have `Java JDK 11` or above installed in your Computer.

2. Download the latest `jar` file, `AIA.jar` from [here](https://github.com/AY2122S2-CS2103T-T17-3/tp/releases).

3. Move the `jar` file to an empty folder in where you want to store the `jar` file and the contacts.

4. Open a shell application (Command Prompt or Terminal etc.) and navigate to the folder created in step 3.

5. Run the `jar` file with the `java -jar` command in the shell application to run the application, e.g. `java -jar AIA.jar`.

6. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.

7. When in doubt, type `help` in the command box or click "Help" in the application bar to pull up the list of available commands.

8. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the information to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is an information which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Dates should be in "YYYY-MM-DD" format, unless stated otherwise. Single digits should be zero padded.<br>
  e.g. `2022-05-17`, `2021-01-01` and `2011-10-10` are in the correct format, while `2022-5-17` and `2021-1-1` are in the wrong format.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Adding a contact: `add`

Adds a contact to the application.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS b/BIRTHDATE [t/TAG]…​`
* `BIRTHDATE` is in "DD-MM-YYYY" format.

<div markdown="span" class="alert alert-primary">:bulb:
**Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 b/1970-01-01` adds the contact with only the compulsory information provided.
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 b/1970-01-01 t/Interviewee t/students` adds the contact with the compulsory information provided and the optional information.

### Listing all contacts : `list`

Shows a list of all contacts in the application.

Format: `list`

### Editing a contact : `edit`

Edits an existing contact in the application.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [b/BIRTHDATE]
[t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list.
  The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.
* `BIRTHDATE` is in "YYYY-MM-DD" format.

Examples:
* `edit 1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
* `edit 2 n/Betsy Crower t/` edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Finding contacts by name: `find`

Find contacts whose names contain any of the given keywords, i.e, partial name works with this feature.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>

### Deleting a contact : `delete`

Deletes the specified contact from the application.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd contact in the application.
* `find Betsy` followed by `delete 1` deletes the 1st contact in the results of the `find` command.

### Assigning a recently contacted date to a contact: `contacted`

Manually logs the date of the most recent interaction with the contact.

Format: `contacted INDEX d/DATE des/description`
* `DATE` is in `YYYY-MM-DD` format.

Example:
* `contacted 23 d/2022-02-11 des/Signed contract` manually logs the following interaction to the 23rd person in the displayed contact list, `Signed contract` on `2022-02-11`.

### Deleting a recently contacted date from a contact: `unlog`

Deletes the specified recently contacted date from a specified contact.

Format: `unlog INDEX del/INDEX`

* First `INDEX` signifies the specified contact.
* The first index refers to the index number shown in the displayed contact list.
* Second `INDEX` signifies the contacted date to delete.
* The second index refers to the index number shown in the contact's displayed recently contacted date list.
* both indexes **must be a positive integer** 1, 2, 3, …​

Example:
* `unlog 2 del/6` deletes the 6th recently contacted date from the 2nd contact in the displayed contact list.

### Listing all recently contacted dates of a contact: `logs`

Lists all recently contacted dates for a specified contact.

Format: `logs INDEX`

* `INDEX` signifies the specified contact.
* The index refers to the index number shown in the displayed contact list.
* both indexes **must be a positive integer** 1, 2, 3, …​

Example:
* `logs 2` lists all recently contacted dates for the 2nd contact in the display contact list.

### Listing contacts that were contacted within days: `within`

Lists all contacts that were contacted within specified range of days.

Format: `within DAYS`
* DAYS must be a positive integer.

Example:
* `within 12` lists all contacts that were last contacted in the past 12 days.

### Displaying contacted contacts later than DAYS: `later`

Display contacts that needs to be contacts after DAYS.

Format: `later d/DAYS`
* DAYS must be a positive integer.

Example:
* `later d/12`

### Adding a reminder to a contact: `remind`

Adds a reminder for a contact.

Format: `remind INDEX r/REMINDER rd/DATE`
* `DATE` is in `YYYY-MM-DD` format.

Example:
* `remind 13 r/dinner rd/2022-09-11`


### Listing reminders of a contact: `reminder`

Lists all reminders under a contact as specified by index.

Format: `reminder INDEX`

Example:
* `reminder 7`

### Viewing contacts by reminder: `reminders`

Displays all contacts with a reminder with the specified date.

Format: `reminders [d/DATE]`
* `DATE` is in `YYYY-MM-DD` format.

Example:
* `reminders d/2023-01-01`

### Deleting a reminder: `forget`

Deletes the specified reminder of a contact as specified by the index.

Format: `forget INDEX r/REMINDER`

Example:
* `forget 5 r/Dinner outing`

### Adding a tag: `tag`

Adds a tag to an existing contact, as specified by the index.

Format: `tag INDEX t/TAG`

Example:
* `tag 27 t/client` adds the "client" tag to the 27th contact in the display contact list.

### Deleting a tag: `untag`

Deletes a tag from an existing contact, as specified by the index.

Format: `untag INDEX t/TAG`

Example:
* `untag 27 t/colleague` deletes the "colleague" tag from the 27th contact in the display contact list.

### Listing all available tags: `tags`

Lists all the available tags assigned to the contacts, in alphabetical order.

Format: `tags`

### Finding contacts with tag: `#`

Finds all the contacts with the specified tag assigned to them.

Format: `#TAG`

Example:
* `#Friends` finds all the contacts that are tagged to "friends".

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Adding automatic recurring reminders `[coming in v1.3]`

_Create a repeating reminder that has a variable repeating period._

--------------------------------------------------------------------------------------------------------------------

## Interesting Features

1. Automatic reminders
    1. Upon opening the application, the application will list a collection of events happening on that day, in
       chronological order.
    1. Alternatively, users can type refresh to check if an event is happening on the current date.
1. Find User
    1. Users can find a contact based on their client name (partial name works)
1. In-app help
    1. Users can receive help via pop up boxes
1. Upon clicking a contact, the command bar populate with the proper command syntax based on the selected contact
1. Saving of data is done automatically


--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format
--------|------------------
**Add a contact** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`
**List all contacts** | `list`
**Edit a contact** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`
**Find a contact by keywords** | `find KEYWORD [MORE_KEYWORDS]`
**Delete a contact** | `delete INDEX`
**Assign date to contact** | `contacted INDEX d/DATE n/description`
**Display contacts within DAYS** | `within d/DAYS`
**Display contacts after DAYS** | `later d/DAYS`
**Add a non-recurring reminder** | `remind INDEX r/REMINDER d/DATE`
**View reminders of a contact** | `reminder INDEX`
**View reminders on a date** | `reminders [d/DAY]`
**Delete a reminder** | `forget INDEX r/REMINDER`
**Edit tags** | `tag INDEX add t/TAG` <br> `tag INDEX delete t/TAG`
**Show all tags** | `tags`
**Find contacts by tags** | `#`
**Exit the program** | `exit`
**Help** | `help`
**Add automatic reminders** | `[coming in v1.3]`
