import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        VolumeManager v = new VolumeManager("Volume Manager");
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        boolean exitLoop = false;
        if (!v.isDataFileEmpty()){
            while (!exitLoop){
                System.out.print("Would you like to retrieve previous data? (y/n) ");
                String answer = sc.nextLine();
                if (answer.equalsIgnoreCase("y")){
                    v.retrieveData();
                    exitLoop = true;
                } else if (answer.equalsIgnoreCase("n")){
                    System.out.print("Are you sure? This action will cause all previous data to be deleted. (y/n) ");
                    answer = sc.nextLine();
                    if (answer.equalsIgnoreCase("y")){
                        v.clearData();
                        exitLoop = true;
                    }
                } else {
                    System.out.println("Invalid answer");
                }
            }
        }

        System.out.println("Welcome to the LVM system! Enter your commands:");
        System.out.println();
        while (!quit){
            System.out.print("cmd# ");
            String input = sc.nextLine();
            String choice;
            if (input.indexOf(" ") == -1){
                choice = input;
            } else {
                choice = input.substring(0, input.indexOf(" "));
            }
            String info = input.substring(input.indexOf(" ") + 1);
            if (choice.equals("install-drive")){
                if (countDelimiters(info) == 1){
                    String name = info.substring(0, info.indexOf(" "));
                    String size = info.substring(info.indexOf(" ") + 1);
                    if (!v.hardDriveAlreadyExist(name)){
                        System.out.println(v.installDrive(name, size));
                    } else {
                        System.out.println("Drive " + name + " already exists");
                        System.out.println();
                    }
                } else {
                    System.out.println("Invalid format. Expected format: install-drive [drive_name] [size]");
                    System.out.println();
                }
            } else if (choice.equals("list-drives")){
                System.out.println(v.listDrives());
            } else if (choice.equals("pvcreate")){
                if (countDelimiters(info) == 1){
                    String name = info.substring(0, info.indexOf(" "));
                    if (!v.pvAlreadyExist(name)){
                        String physicalHardDriveName = info.substring(info.indexOf(" ") + 1);
                        if (!v.hardDriveDoesNotExist(physicalHardDriveName)){
                            if (!v.hardDriveAlreadyAssociated(physicalHardDriveName)){
                                System.out.println(v.pvCreate(name, physicalHardDriveName));
                            } else {
                                System.out.println(physicalHardDriveName + " is already associated with a physical volume");
                                System.out.println();
                            }
                        } else {
                            System.out.println("Drive " + physicalHardDriveName + " does not exist");
                            System.out.println();
                        }
                    } else {
                        System.out.println("Physical volume " + name + " already exists");
                        System.out.println();
                    }
                } else {
                    System.out.println("Invalid format. Expected format: pvcreate [pv_name] [drive_name]");
                    System.out.println();
                }
            } else if (choice.equals("pvlist")){
                System.out.println(v.pvlist());
            } else if (choice.equals("vgcreate")){
                if (countDelimiters(info) == 1){
                    String name = info.substring(0, info.indexOf(" "));
                    if (!v.vgAlreadyExist(name)){
                        String physicalVolumeName = info.substring(info.indexOf(" ") + 1);
                        if (!v.physicalVolumeDoesNotExist(physicalVolumeName)){
                            if (!v.physicalVolumeAlreadyAssociated(physicalVolumeName)){
                                System.out.println(v.vgCreate(name, physicalVolumeName));
                            } else {
                                System.out.println("Physical volume " + physicalVolumeName + " is already associated with a volume group");
                                System.out.println();
                            }
                        } else {
                            System.out.println("Physical volume " + physicalVolumeName + " does not exist");
                            System.out.println();
                        }
                    } else {
                        System.out.println("Volume group " + name + " already exists");
                        System.out.println();
                    }
                } else {
                    System.out.println("Invalid format. Expected format: vgcreate [vg_name] [pv_name]");
                    System.out.println();
                }
            } else if (choice.equals("vgextend")){
                if (countDelimiters(info) == 1){
                    String vgName = info.substring(0, info.indexOf(" "));
                    if (!v.volumeGroupDoesNotExist(vgName)){
                        String physicalVolumeName = info.substring(info.indexOf(" ") + 1);
                        if (!v.physicalVolumeDoesNotExist(physicalVolumeName)){
                            if (!v.physicalVolumeAlreadyAssociated(physicalVolumeName)){
                                System.out.println(v.vgextend(vgName, physicalVolumeName));
                            } else {
                                System.out.println("Physical volume " + physicalVolumeName + " is already associated with a volume group");
                                System.out.println();
                            }
                        } else {
                            System.out.println("Physical volume " + physicalVolumeName + " does not exist");
                            System.out.println();
                        }
                    } else {
                        System.out.println("Volume group " + vgName + " does not exist");
                        System.out.println();
                    }
                } else {
                    System.out.println("Invalid format. Expected format: vgextend [vg_name] [pv_name]");
                    System.out.println();
                }
            } else if (choice.equals("vglist")){
                System.out.println(v.vglist());
            } else if (choice.equals("lvcreate")){
                if (countDelimiters(info) == 2){
                    String name = info.substring(0, info.indexOf(" "));
                    if (!v.logicalVolumeAlreadyExists(name)){
                        info = info.substring(info.indexOf(" ") + 1);
                        String size = info.substring(0, info.indexOf(" "));
                        String volumeGroupName = info.substring(info.indexOf(" ") + 1);
                        if (!v.volumeGroupDoesNotExist(volumeGroupName)){
                            System.out.println(v.lvcreate(name, size, volumeGroupName));
                        } else {
                            System.out.println("Volume group " + volumeGroupName + " does not exist");
                            System.out.println();
                        }
                    } else {
                        System.out.println("Logical volume " + name + " already exists");
                        System.out.println();
                    }
                } else {
                    System.out.println("Invalid format. Expected format: lvcreate [lv_name] [size] [vg_name]");
                    System.out.println();
                }
            } else if (choice.equals("lvlist")){
                System.out.println(v.lvlist());
            } else if (choice.equals("exit")){
                System.out.println("Saving data.... Good-bye!");
                v.saveData();
                quit = true;
            } else {
                System.out.println("Invalid command");
                System.out.println();
            }
        }
    }
    public static int countDelimiters(String info){
        int countDelimiters = 0;
        String temp = info;
        int index = temp.indexOf(" ");
        while (index != -1){
            countDelimiters++;
            temp = temp.substring(index + 1);
            index = temp.indexOf(" ");
        }
        return countDelimiters;
    }
}
