public class TowerOfHanoi {
    //Move n disk from "FromRod" to "ToRod" using extra rod "auxRod"
    public static void towerOfHanoi(int n, int fromRod, int toRod, int auxRod) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + fromRod + " to " + toRod);
            return;
        }
        //Move top n-1 disk from "FromRod" to "auxRod" using extra rod "toRod"
        towerOfHanoi(n - 1, fromRod, auxRod, toRod);
        //Move nth last disk from fromRod to toRod
        System.out.println("Move disk " + n + " from " + fromRod + " to " + toRod);
        //Move top n-1 disks from "auxRod" to "toRod"
        towerOfHanoi(n - 1, auxRod, toRod, fromRod);
    }

    public static void main(String[] args) {
        int n = 4;
        towerOfHanoi(n, 1, 2, 3);
    }
}
