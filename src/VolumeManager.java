import java.io.File;
import java.io.PrintWriter;
import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

public class VolumeManager {
    private String name;
    private String uuid;
    //save  the data of all the objects here
    private static ArrayList<PhysicalHardDrive> physicalHardDriveArrayList = new ArrayList<PhysicalHardDrive>();
    private static ArrayList<PhysicalVolume> physicalVolumeArrayList = new ArrayList<PhysicalVolume>();
    private static ArrayList<VolumeGroup> volumeGroupArrayList = new ArrayList<VolumeGroup>();
    private static ArrayList<LogicalVolume> logicalVolumeArrayList = new ArrayList<LogicalVolume>();

    public VolumeManager(String name){
        this.name = name;
        uuid = generateUUID();
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String generateUUID(){
        UUID u = UUID.randomUUID();
        return u.toString();
    }
    public PhysicalHardDrive findHardDrive(String name){
        for (PhysicalHardDrive p : physicalHardDriveArrayList){
            if (p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }
    public void addPhysicalHardDrive(PhysicalHardDrive p){
        physicalHardDriveArrayList.add(p);
    }
    public PhysicalVolume findPhysicalVolume(String name){
        for (PhysicalVolume p : physicalVolumeArrayList){
            if (p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }
    public void addPhysicalVolume(PhysicalVolume p){
        physicalVolumeArrayList.add(p);
    }
    public VolumeGroup findVolumeGroup(String name){
        for (VolumeGroup v : volumeGroupArrayList){
            if (v.getName().equals(name)){
                return v;
            }
        }
        return null;
    }
    public void addVolumeGroup(VolumeGroup v){
        volumeGroupArrayList.add(v);
    }
    //-------------------------------Commands-----------------------------------
    public String installDrive(String name, String size){
        /*
        - prevent users from entering the same name
         */
        PhysicalHardDrive p = new PhysicalHardDrive(name, size);
        addPhysicalHardDrive(p);
        return "drive " + name + " installed\n";
    }
    public String listDrives(){
        String str = "";
        for (int i = 0; i < physicalHardDriveArrayList.size(); i++){
            str += physicalHardDriveArrayList.get(i).toString() + "\n";
        }
        return str;
    }
    public String pvCreate(String name, String physicalHardDriveName){
        PhysicalVolume pv = new PhysicalVolume(name, physicalHardDriveName);
        addPhysicalVolume(pv);
        findHardDrive(physicalHardDriveName).setPhysicalVolume(pv); //sets the hard drive to an associated physical volume
        return name + " created\n";
    }
    public String pvlist(){
        String str = "";
        for (int i = 0; i < physicalVolumeArrayList.size(); i++){
            str += physicalVolumeArrayList.get(i).toString() + "\n";
        }
        return str;
    }
    public String vgCreate(String name, String physicalVolumeName){
        VolumeGroup vg = new VolumeGroup(name, physicalVolumeName);
        addVolumeGroup(vg);
        findPhysicalVolume(physicalVolumeName).setVolumeGroup(vg); //sets the physical volume to an associated volume group
        return name + " created\n";
    }
    public String vgextend(String vgname, String physicalVolumeName){
        findVolumeGroup(vgname).addPhysicalVolumeTovg(findPhysicalVolume(physicalVolumeName)); //be careful of which pv list im adding to
        findPhysicalVolume(physicalVolumeName).setVolumeGroup(findVolumeGroup(vgname));
        return physicalVolumeName + " added to " + vgname + "\n";
    }
    public String vglist(){
        String str = "";
        for (int i = 0; i < volumeGroupArrayList.size(); i++){
            str += volumeGroupArrayList.get(i).toString() + "\n";
        }
        return str;
    }
    public String lvcreate(String lvName, String size, String vgName){
        VolumeGroup vg = findVolumeGroup(vgName);
        int intSize = Integer.valueOf(size.substring(0, size.indexOf("G")));

        if (intSize <= vg.getAvailable()){
            LogicalVolume lv = new LogicalVolume(lvName, size, vgName);
            lv.setVolumeGroup(vg);
            vg.addLogicalVolumeTovg(lv);
            logicalVolumeArrayList.add(lv);
            return lvName + " created\n";
        } else {
            return vgName + " does not have enough space\n";
        }
    }
    public String lvlist(){
        String str = "";
        for (int i = 0; i < logicalVolumeArrayList.size(); i++){
            str += logicalVolumeArrayList.get(i).toString() + "\n";
        }
        return str;
    }
    //-------------------------------user input checks----------------------------------
    public boolean hardDriveAlreadyExist(String name){
        for (PhysicalHardDrive p : physicalHardDriveArrayList){
            if (p.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    public boolean pvAlreadyExist(String name){
        for (PhysicalVolume p : physicalVolumeArrayList){
            if (p.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    public boolean hardDriveDoesNotExist(String hardDriveName){
        for (PhysicalHardDrive p : physicalHardDriveArrayList){
            if (p.getName().equals(hardDriveName)){
                return false;
            }
        }
        return true;
    }
    public boolean hardDriveAlreadyAssociated(String hardDriveName){
        for (PhysicalVolume p : physicalVolumeArrayList){
            if (p.getPhysicalHardDrive().getName().equals(hardDriveName)){
                return true;
            }
        }
        return false;
    }
    public boolean vgAlreadyExist(String name){
        for (VolumeGroup v : volumeGroupArrayList){
            if (v.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    public boolean physicalVolumeDoesNotExist(String name){
        for (PhysicalVolume p : physicalVolumeArrayList){
            if (p.getName().equals(name)){
                return false;
            }
        }
        return true;
    }
    public boolean physicalVolumeAlreadyAssociated(String name){
        if (findPhysicalVolume(name).getVolumeGroup() == null){
            return false;
        }
        return true;
    }
    public boolean volumeGroupDoesNotExist(String name){
        for (VolumeGroup v : volumeGroupArrayList){
            if (v.getName().equals(name)){
                return false;
            }
        }
        return true;
    }
    public boolean logicalVolumeAlreadyExists(String name){
        for (LogicalVolume l : logicalVolumeArrayList){
            if (l.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    //-------------------------------saving and retrieving data-------------------------------
    public String getAllSavedData(){
        String str = "";
        str += physicalHardDriveArrayList.size() + "\n";
        for (PhysicalHardDrive p : physicalHardDriveArrayList){
            str += p.dataInfo() + "\n";
        }
        str += physicalVolumeArrayList.size() + "\n";
        for (PhysicalVolume p : physicalVolumeArrayList){
            str += p.dataInfo() + "\n";
        }
        str += volumeGroupArrayList.size() + "\n";
        for (VolumeGroup v : volumeGroupArrayList){
            str += v.dataInfo() + "\n";
        }
        str += logicalVolumeArrayList.size() + "\n";
        for (LogicalVolume l : logicalVolumeArrayList){
            str += l.dataInfo() + "\n";
        }
        return str;
    }
    public void saveData(){
        String data = getAllSavedData();
        try {
            PrintWriter pw = new PrintWriter("savedData.txt");

            pw.print(data);
            pw.close();
        }
        catch(Exception e) {
            e.getStackTrace();
        }
    }
    public void clearData() throws Exception{
        File file = new File("savedData.txt");
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();
    }
    public boolean isDataFileEmpty(){
        File file = new File("savedData.txt");
        return file.length() == 0;
    }
    public void retrieveData() throws Exception{
        File file = new File("savedData.txt");
        Scanner sc = new Scanner(file);
        //retrieving data for physical hard drive
        String[] physicalHardDriveData = new String[Integer.valueOf(sc.nextLine())];
        int counter = 0;
        String str = sc.nextLine();
        while(!isInteger(str))
        {
            physicalHardDriveData[counter] = str;
            counter++;
            str = sc.nextLine();
        }
        //retrieving data for physical volume
        String[] physicalVolumeData = new String[Integer.valueOf(str)];
        counter = 0;
        str = sc.nextLine();
        while(!isInteger(str))
        {
            physicalVolumeData[counter] = str;
            counter++;
            str = sc.nextLine();
        }
        //retrieving data for volume group
        String[] volumeGroupData = new String[Integer.valueOf(str)];
        counter = 0;
        str = sc.nextLine();
        while(!isInteger(str))
        {
            volumeGroupData[counter] = str;
            counter++;
            str = sc.nextLine();
        }
        //retrieving data for logical volume
        String[] logicalVolumeData = new String[Integer.valueOf(str)];
        counter = 0;
        while(sc.hasNextLine())
        {
            logicalVolumeData[counter] = sc.nextLine();
            counter++;
        }
//        System.out.println("Physical HardDrive Data:");
//        System.out.println(Arrays.toString(physicalHardDriveData));
//        System.out.println("Physical volume Data:");
//        System.out.println(Arrays.toString(physicalVolumeData));
//        System.out.println("Volume Group Data:");
//        System.out.println(Arrays.toString(volumeGroupData));
//        System.out.println("Logical Volume Data:");
//        System.out.println(Arrays.toString(logicalVolumeData));
        //setting physical hardDrive objects (name only first)

        for (String s : physicalHardDriveData){
            String name = s.substring(0, s.indexOf(";"));
            s = s.substring(s.indexOf(";") + 1);
            String size = s.substring(0, s.indexOf(";"));
            String pvname = s.substring(s.indexOf(";") + 1);
            PhysicalHardDrive p = new PhysicalHardDrive(name, size);
            p.setPhysicaVolumeName(pvname);
            physicalHardDriveArrayList.add(p);
        }
        for (String s : physicalVolumeData){
            String name = s.substring(0, s.indexOf(";"));
            s = s.substring(s.indexOf(";") + 1);
            String uuid = s.substring(0, s.indexOf(";"));
            s = s.substring(s.indexOf(";") + 1);
            String physicalHardDriveName = s.substring(0, s.indexOf(";"));
            String vgname = s.substring(s.indexOf(";") + 1);
            PhysicalVolume p = new PhysicalVolume(name, physicalHardDriveName);
            p.setName(name);
            p.setUuid(uuid);
            p.setVolumeGroupName(vgname);
            physicalVolumeArrayList.add(p);
        }
        //now that physical volume objects are created, the PhysicalVolume instance variable in physicalHardDrive can be set
        for (PhysicalHardDrive p : physicalHardDriveArrayList){
            if (!p.getPhysicaVolumeName().equals("")){
                p.setPhysicalVolume(findPhysicalVolume(p.getPhysicaVolumeName()));
            }
        }
        for (String s : volumeGroupData){
            String name = s.substring(0, s.indexOf(";"));
            String uuid = s.substring(s.indexOf(";") + 1);
            VolumeGroup vg = new VolumeGroup(name);
            vg.setUuid(uuid);
            volumeGroupArrayList.add(vg);
        }
        //now that volume group objects are created, the volumeGroup instance variable in physicalVolume can be set
        for (PhysicalVolume p : physicalVolumeArrayList){
            if (!p.getVolumeGroupName().equals("")){
                p.setVolumeGroup(findVolumeGroup(p.getVolumeGroupName()));
            }
        }
        //now that physicalVolume actually has a volumeGroup object, we can set the physicalvolume arrayList in volumeGroup
        for (VolumeGroup v : volumeGroupArrayList){
            for (PhysicalVolume p : physicalVolumeArrayList){
                if (v.getName().equals(p.getVolumeGroupName())){
                    v.addPhysicalVolumeTovg(p);
                }
            }
        }
        for (String s : logicalVolumeData){
            String name = s.substring(0, s.indexOf(";"));
            s = s.substring(s.indexOf(";") + 1);
            String uuid = s.substring(0, s.indexOf(";"));
            s = s.substring(s.indexOf(";") + 1);
            String size = s.substring(0, s.indexOf(";"));
            String vgname = s.substring(s.indexOf(";") + 1);
            LogicalVolume l = new LogicalVolume(name, size, vgname);
            findVolumeGroup(vgname).addLogicalVolumeTovg(l);
            logicalVolumeArrayList.add(l);
        }
    }
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
}
