#patterns---->
SOLID principles-->
------------------------------------------------------------
S-single responsibility 
a class should be having single responsibilty not more than one 
------------------------------------------------------------
O-open/closed
class should be open to extension but should be closed for the modififcation 
------------------------------------------------------------
L-liskov substution principle
dereived classes must be substitable for the parent class without breaking stuff
interface Bird {
    void layEggs();
}

interface FlyingBird extends Bird {
    void fly();
}

class Sparrow implements FlyingBird {
    public void layEggs() {}
    public void fly() {}
}

class Ostrich implements Bird {
    public void layEggs() {}
}
------------------------------------------------------------
I-interface segregation principle

clients shoudl not be foreced to use interfaces they dont have functionality or use in it
interface Worker {
    void work();
    void eat();
}

class Robot implements Worker {
    public void work() {}
    public void eat() {} // why should robot eat?
}
------------------------------------------------------------
D-depndency inversion principle 

high level modulkes shouldnt be dependent upin the low level they shoyld be on interfsces only 

interface Keyboard {
    void type();
}

class WiredKeyboard implements Keyboard {
    public void type() {}
}

class Computer {
    private Keyboard keyboard;

    public Computer(Keyboard keyboard) {
        this.keyboard = keyboard;
    }
}
------------------------------------------------------------
