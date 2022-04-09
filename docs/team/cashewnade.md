---
layout: page
title: Shi Kexin's Project Portfolio Page
---

### Project: Automated Insurance Assistant (AIA)

Automated Insurance Assistant (AIA) is a desktop application that helps Insurance Agents to manage their client contacts.
Instead of hiring a secretary to handle day-to-day activities, using AIA can help you achieve the following:

- Catalogue and easily retrieve saved data on clients based on specific categories
- Keep track of important events and time sensitive tasks to do for each client (e.g. client birthdays)
- Keep records of previous interactions with each client

This is a brownfield project that closely follows the structure of
[AB-3](https://se-education.org/addressbook-level3/DeveloperGuide.html), with an addition of approximately 10KLoC.

Given below are my contributions to the project.

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=cashewnade&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* Features:
  * `Reminder` class for reminder features, along with the corresponding parser classes, command classes, and
    `ReminderList` that captures a list of `Reminder` objects. [#51](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/51)
  * Added command for the addition and deletion of reminders using `remind` and `forget` respectively. Added command for
    listing all reminders based on dates and contacts using `reminders` and `reminder` respectively. [#93](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/93)
  * `JsonAdaptedReminder` for Jackson-friendly version of `Reminder`.
  * Testing:
    * Tests for `Reminder` and its corresponding parser classes and command classes.
    * Smoke tests

**Documentation**:
* User Guide
  * Wrote the initial v1.1 draft for user guide. [#22](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/22)
  * Reformatted the user guide into markdown format.
* Developer Guide
  * Updated the documentation of reminders features. [#215](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/215/)

**Team-based tasks**:
* PRs reviewed (with non-trivial review comments): 
  [#15](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/15), 
  [#19](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/19),
  [#169](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/169),
  [#179](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/179)
* Attended debugging sessions held either on Discord or Zoom
