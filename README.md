ehud
====

Self organizing network simulator. Written to test various algorithmic implementations of SON functions

Please use eclipse to import the project into your workspace. A short tutorial for doing so can be found at 
http://docs.joomla.org/Working_with_Git_and_Eclipse

Problem Description (Story Board)
=================================
Suppose an aircraft carrying routers is flying over a field. The airplane opens its cargo bay doors and releases the routers. There is no control over where the routers will land, how fast they will hit the ground or in which position. To ensure survivability each router has a small parachute that deploys at a certain altitude but some parachutes are faulty and fail to deploy resulting in the router smashing into the ground. If the router hits the ground too fast something will break such as the antenna; causing a mechanical failure in the device. Each router has a battery that will last a certain amount of time. Not all routers will switch on simultaneously, some routers will switch on after a few hours. Animals on the field may run on the router causing a functional router to stop operating or the battery may die.
The field may have tall buildings, high mountains, rocks made of radio absorbing material, or active jammers that jam a radio signal. These are treated as interference fields. Interference fields are for a certain frequency range; that is two frequencies cannot be simultaneously jammed.
Now we design a system that will enable two people on the field to communicate using the routers as the backbone network. Assume there is no other network in the area previously.

Problem Description (Technical)
===============================
Given multiple radios with a certain duration of battery life, a certain mean-time-to-failure (MTTF), an uncertain operating environment design a self organizing network backbone that will allow communication using packet hop networking. The parameters for organization maybe a combination of battery life, failure resiliency, coverage area or may focus on any one factor specifically.

Proposed Solution
==================
The following project looks at Self Organizing network functions (SON) implemented in a software model.
The project focusses on implementing efficient algorithms for the following network func- tions in Java
1. self-configuration
  1. Physical Cell ID Configuration, 
  2. Self capability detection,
2. self-optimization
  1. Automatic Neighbor Relations (ANR),
  2. Inter-Cell Interference Coordination (ICIC),
  3. Mobility Robustness Optimization (MRO),
  4. Mobility Load Balancing Optimization (MLB)
3. self-healing
  1. Cell outage,
  2. Self-recovery of network element (NE)
  3. 
  
