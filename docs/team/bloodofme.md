---
layout: page
title: Nadine Chiun's Project Portfolio Page
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

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=bloodofme&breakdown=true)
* Features:
  - `ContactedInfo` class for recent interaction feature and its corresponding command and parser classes [\#67](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/67)
  - Added command for adding of recent interaction `log` [\#46](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/46)
  - Added command for listing all recent interaction for a specific client `logs` [\#84](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/84)
  - Delete selected recent interaction for a specific client `unlog` [\#89](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/89)
  - `JsonAdaptedContactedInfo` for Jackson-friendly version of ContactedInfo [\#67](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/67)
  - Testing:
    - Tests for `ContactedInfo` and its corresponding command and parser classes
    - Tests for `DeleteContactedInfoCommand` and `ListContactedInfoCommand`
    - Smoke tests and bug fixes

**Documentation**:
* README:
  * Update README for project according to AIA [\#17](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/17)
* AboutUs:
  * Update AboutUs for project according to team member's roles and information [\#17](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/17)
* UserGuide:
  * Contributed to the userguide by adding screenshots [\#132](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/132)
  * Contributed to the userguide by updating examples related to insurance agents [\#115](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/115)
  * Updated userguide based on comments made by test readers [\#165](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/165)

**Team-based tasks**:
* PRs reviewed (with non-trivial review comments): [\#15](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/15), [\#16](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/16),
[\#19](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/19), [\#20](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/20), [\#21](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/21),
[\#59](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/59), [\#70](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/70), [\#72](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/72),
[\#109](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/109), [\#120](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/120), [\#123](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/123),
[\#129](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/129), [\#185](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/185), [\#188](https://github.com/AY2122S2-CS2103T-T17-3/tp/pull/188)
* Attended debugging sessions held either on Discord or Zoom
* Consolidated and produced demo video
