---
layout: page
title: User Guide
---

Automated Insurance Assistant (AIA) is a desktop application that aims to serve as an easy alternative to a customer relationship management system for Insurance Agents to manage their client contacts.
Insurance agents can use this application to keep track of client meetings and important dates of each client.

Usage of this application is optimised for use via a Command Line Interface (CLI) while still displaying information to users in an intuitive manner, through a Graphical User Interface (GUI).
If you can type fast, AIA can reduce the amount of administrative work you need to do for each client.

This user guide offers the following to insurance agents or other users trying to learn how to use the application:
- A quick start to running the application
- All available features
- Examples on how to use each feature

Bold words refer to commands to be performed by users.
Words in quotations are files, file types or dependencies.

----
<div style="page-break-after: always;"></div>

## Table of Contents

* [Quick Start](#quick-start)
* [Features](#features)
  - [Adding a contact: `add`](#adding-a-contact-add)
  - [Listing all contacts: `list`](#listing-all-contacts-list)
  - [Editing a contact: `edit`](#editing-a-contact-edit)
  - [Finding contacts by name: `find`](#finding-contacts-by-name-find)
  - [Deleting a contact: `delete`](#deleting-a-contact-delete)
  - [Adding a tag: `tag`](#adding-a-tag-tag)
  - [Deleting a tag: `untag`](#deleting-a-tag-untag)
  - [Listing all available tags: `tags`](#listing-all-available-tags-tags)
  - [Finding contacts with tag: `#`](#finding-contacts-with-tag-)
  - [Assigning a recently contacted date to a contact: `contacted`](#assigning-a-recently-contacted-date-to-a-contact-contacted)
  - [Deleting a recently contacted date from a contact: `unlog`](#deleting-a-recently-contacted-date-from-a-contact-unlog)
  - [Listing all recently contacted dates of a contact: `logs`](#listing-all-recently-contacted-dates-of-a-contact-logs)
  - [Listing contacts that were contacted within days: `within`](#listing-contacts-that-were-contacted-within-days-within)
  - [Displaying contacts contacted more than a specified number of days ago: `after`](#displaying-contacts-contacted-more-than-a-specified-number-of-days-ago-after)
  - [Adding a reminder to a contact: `remind`](#adding-a-reminder-to-a-contact-remind)
  - [Listing reminders of a contact: `reminder`](#listing-reminders-of-a-contact-reminder)
  - [Viewing contacts by reminder: `reminders`](#viewing-contacts-by-reminder-reminders)
  - [Deleting a reminder: `forget`](#deleting-a-reminder-forget)
  - [Exiting the program: `exit`](#exiting-the-program-exit)
  - [Saving the data](#saving-the-data)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Quick Start

1. Ensure you have "Java JDK 11" or above installed in your Computer.

2. Download the latest "jar" file, "AIA.jar" from [here](https://github.com/AY2122S2-CS2103T-T17-3/tp/releases).

3. Move the "jar" file to an empty folder in where you want to store the "jar" file and the contacts.

4. Open a shell application (Command Prompt or Terminal etc.) and navigate to the folder created in step 3.

5. Run the "jar" file with the **java -jar** command in the shell application to run the application, e.g. **java -jar AIA.jar**.

6. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.

7. When in doubt, type `help` in the command box or click "Help" in the application bar to pull up the list of available commands.

8. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Markup words in `UPPER_CASE` are the information to be entered by the user.<br>
  e.g. in `add n/NAME`, `NAME` is an information which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/Client` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/Client`, `t/Client t/PremiumMember` etc.

* User input can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If an input is expected only once in for a specific command, but you specified it multiple times, only the last occurrence of
  the input will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Dates should be in "YYYY-MM-DD" format, unless stated otherwise. Single digits should be zero padded.<br>
  e.g. `2022-05-17`, `2021-01-01` and `2011-10-10` are in the correct format, while `2022-5-17` and `2021-1-1` are in the wrong format.
* `INDEX` used in the different commands refer to the index number shown in the displayed person list.

  ❗The `INDEX` must be a positive integer i.e. 1,2,3,...

</div>

<div style="page-break-after: always;"></div>

### Adding a contact: `add`

Adds a contact to the application. 

* Upon adding a person, a default recently interacted date and description would be automatically generated.
  * The default date would be the day the contact was added, and default description would be "First Interaction".

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS b/BIRTH_DATE [t/TAG]…​`
* `BIRTH_DATE` should be in the specified date format.

<div markdown="span" class="alert alert-primary">:bulb:
**Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 b/1970-01-01` adds the contact with only the compulsory information provided.
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 b/1970-01-01 t/Client t/BasicMember` adds the contact with the compulsory information provided and the optional information.

[Return to Table of Contents](#table-of-contents)

### Listing all contacts: `list`

Shows a list of all contacts in the application.

Format: `list`

[Return to Table of Contents](#table-of-contents)
<div style="page-break-after: always;"></div>

### Editing a contact: `edit`

Edits an existing contact in the application.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [b/BIRTH_DATE]
[t/TAG]…​`

* Edits the person at the specified `INDEX`.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.
* `BIRTH_DATE` should be in the specified date format.

Examples:
* `edit 1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
* `edit 2 n/Betsy Crower t/` edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

[Return to Table of Contents](#table-of-contents)
<div style="page-break-after: always;"></div>

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

[Return to Table of Contents](#table-of-contents)

### Deleting a contact: `delete`

Deletes the specified contact from the application.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.

Examples:
* `list` followed by `delete 2` deletes the 2nd contact in the application.
* `find Betsy` followed by `delete 1` deletes the 1st contact in the results of the `find` command.

[Return to Table of Contents](#table-of-contents)

### Adding a tag: `tag`

Adds a tag to an existing contact, as specified by the index.

Format: `tag INDEX t/TAG`

Example:
* `tag 27 t/client` adds the "client" tag to the 27th contact in the display contact list.

[Return to Table of Contents](#table-of-contents)

### Deleting a tag: `untag`

Deletes a tag from an existing contact, as specified by the index.

Format: `untag INDEX t/TAG`

Example:
* `untag 27 t/client` deletes the "client" tag from the 27th contact in the display contact list.

[Return to Table of Contents](#table-of-contents)

### Listing all available tags: `tags`

Lists all the available tags assigned to the contacts, in alphabetical order.

Format: `tags`

[Return to Table of Contents](#table-of-contents)

### Finding contacts with tag: `#`

Finds all the contacts with the specified tag assigned to them.

Format: `#TAG`

Example:
* `#client` finds all the contacts that are tagged to "client".

[Return to Table of Contents](#table-of-contents)

### Assigning a recently contacted date to a contact: `contacted`

Manually logs the date of the most recent interaction with the contact, along with a short description of the purpose of interaction.

Format: `contacted INDEX d/DATE des/description`
* `DATE` should be in the specified date format.

Example:
* `contacted 23 d/2022-02-11 des/Signed contract` manually logs the following interaction to the 23rd person in the displayed contact list, `Signed contract` on `2022-02-11`.

[Return to Table of Contents](#table-of-contents)

### Deleting a recently contacted date from a contact: `unlog`

Deletes the specified recently contacted date from a specified contact.

Format: `unlog INDEX del/INDEX`

* `INDEX` signifies the specified contact.
* `INDEX` refers to the index number shown in the displayed contact list.
* `del/INDEX` signifies the contacted date to delete.
* `del/INDEX` refers to the index number shown in the contact's displayed recently contacted date list.

Example:
* `unlog 2 del/6` deletes the 6th recently contacted date from the 2nd contact in the displayed contact list.

[Return to Table of Contents](#table-of-contents)

### Listing all recently contacted dates of a contact: `logs`

Lists all recently contacted dates for a specified contact.

Format: `logs INDEX`

* `INDEX` signifies the specified contact.

Example:
* `logs 2` lists all recently contacted dates for the 2nd contact in the display contact list.

[Return to Table of Contents](#table-of-contents)

### Listing contacts that were contacted within days: `within`

Lists all contacts that were contacted within specified range of days.

Format: `within DAYS`
* DAYS must be a positive integer.

Example:
* `within 12` lists all contacts that were last contacted in the past 12 days.

[Return to Table of Contents](#table-of-contents)

### Displaying contacts contacted more than a specified number of days ago: `after`

Display contacts that have been previously contacts after a specified number of DAYS.

Format: `after DAYS`
* DAYS must be a positive integer.

Example:
* `after 12`

[Return to Table of Contents](#table-of-contents)

### Adding a reminder to a contact: `remind`

Adds a reminder for a contact.

* `INDEX` signifies the specified contact.
* `INDEX` refers to the index number shown in the displayed contact list.

Format: `remind INDEX r/REMINDER rd/DATE`
* `DATE` should be in the specified date format.

Example:
* `remind 13 r/phone call rd/2022-09-11`

[Return to Table of Contents](#table-of-contents)

### Listing reminders of a contact: `reminder`

Lists all reminders under a contact as specified by index.

Format: `reminder INDEX`

Example:
* `reminder 7`

[Return to Table of Contents](#table-of-contents)
<div style="page-break-after: always;"></div>

### Viewing contacts by reminder: `reminders`

Displays all contacts with a reminder with the specified date.

Format: `reminders [d/DATE]`
* `DATE` should be in the specified date format.

Example:
* `reminders d/2023-01-01`

[Return to Table of Contents](#table-of-contents)

### Deleting a reminder: `forget`

Deletes the specified reminder of a contact as specified by the index.

Format: `forget INDEX r/REMINDER`

Example:
* `forget 5 r/Discussion of contract`

[Return to Table of Contents](#table-of-contents)

### Exiting the program: `exit`

Exits the program.

Format: `exit`

[Return to Table of Contents](#table-of-contents)

### Saving the data

The application's data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
If you made a mistake while manually editing the saved data, a backup save file would be generated in the same folder.

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Command summary

| Action                                              | Format                                                                                |
|-----------------------------------------------------|---------------------------------------------------------------------------------------|
| **Add a contact**                                   | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS b/BIRTH_DATE [t/TAG]…​`                  |
| **List all contacts**                               | `list`                                                                                |
| **Edit a contact**                                  | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [b/BIRTH_DATE] [t/TAG]…​` |
| **Find a contact by keywords**                      | `find KEYWORD [MORE_KEYWORDS]`                                                        |
| **Delete a contact**                                | `delete INDEX`                                                                        |
| **Add a tag**                                       | `tag INDEX t/TAG`                                                                     |
| **Delete a tag**                                    | `untag INDEX t/TAG`                                                                   |
| **Show all tags**                                   | `tags`                                                                                |
| **Find contacts by tags**                           | `#`                                                                                   |
| **Assign date to contact**                          | `contacted INDEX d/DATE des/description`                                              |
| **Delete a recently contacted date from a contact** | `unlog INDEX del/INDEX`                                                               |
| **List all recently contacted date of a contact**   | `logs INDEX`                                                                          |
| **Display contacts within DAYS**                    | `within DAYS`                                                                         |
| **Display contacts after DAYS**                     | `after DAYS`                                                                          |
| **Display birthdays occurring today**               | `birthdays`                                                                           |
| **Add a non-recurring reminder**                    | `remind INDEX r/REMINDER rd/DATE`                                                     |
| **View reminders of a contact**                     | `reminder INDEX`                                                                      |
| **View reminders on a date**                        | `reminders [rd/DATE]`                                                                 |
| **Delete a reminder**                               | `forget INDEX r/REMINDER`                                                             |
| **Exit the program**                                | `exit`                                                                                |
| **Help**                                            | `help`                                                                                |

[Return to Table of Contents](#table-of-contents)
