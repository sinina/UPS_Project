package fileIO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import checkPassword.CheckPasswordMethod;


public class FileIO {

   public ArrayList readDB(String fileName) {
      ArrayList arr = new ArrayList();
      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName + ".txt"));) {
         arr = (ArrayList) ois.readObject();

      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
      } catch (IOException e) {
         // TODO Auto-generated catch block
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      // for(int i=0;i<arr.size();i++){
      // System.out.println(arr.get(i));
      // }
      return arr;
   }

   public void editDB(String fileName, Object o) {
      ArrayList arr = new ArrayList();
      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName + ".txt"));) {
         arr = (ArrayList) ois.readObject();
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         try {
            new FileOutputStream(fileName + ".txt");
         } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
      } catch (IOException e) {
         // TODO Auto-generated catch block
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      arr.add(o);

      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName + ".txt"));) {
         oos.writeObject(arr);
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      // System.out.println("저장");
   }

   public void writeDB(String fileName, ArrayList list) {
      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName + ".txt"));) {
         oos.writeObject(list);
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public void removeObject(String fileName, Object o) {
      ArrayList arr = new ArrayList();
      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName + ".txt"));) {
         arr = (ArrayList) ois.readObject();
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
      } catch (IOException e) {
         // TODO Auto-generated catch block
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      for (int i = 0; i < arr.size(); i++) {
         if (arr.get(i).toString().equals(o.toString())) {
            arr.remove(i);
         }
      }

      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName + ".txt"));) {
         oos.writeObject(arr);
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   public void removeIndexObject(String fileName, int index) {
      ArrayList arr = new ArrayList();
      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName + ".txt"));) {
         arr = (ArrayList) ois.readObject();
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
      } catch (IOException e) {
         // TODO Auto-generated catch block
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      arr.remove(index);

      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName + ".txt"));) {
         oos.writeObject(arr);
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   


}