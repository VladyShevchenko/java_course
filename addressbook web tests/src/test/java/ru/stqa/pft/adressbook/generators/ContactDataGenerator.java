package ru.stqa.pft.adressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.adressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter (names = "-c", description = "Contact count")
    public int count;
    @Parameter (names = "-f", description = "Target file")
    public String file;
    @Parameter (names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("scv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")) {
            SaveAsXml(contacts, new File(file));
        } else if (format.equals("json")) {
            SaveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void SaveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void SaveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for(ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s\n", contact.getFirsname(), contact.getLastname(), contact.getHomePhone()));
        }
        writer.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        File photo = new File("src/test/resources/test.png");
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData()
                    .withFirsname(String.format("Firsname %s", i))
                    .withLastname(String.format("Lastname %s", i))
                    .withHomePhone(String.format("+1234567890%s", i))
                    .withMobilePhone(String.format("+0987654321%s", i))
                    .withWorkPhone(String.format("+0000000000%s", i))
                    .withAddress(String.format("Ukraine%s", i))
                    .withEmail(String.format("test1@email.com", i))
                    .withEmail2(String.format("test2@email.com", i))
                    .withEmail3(String.format("test3@email.com", i)));
        }
        return contacts;
    }
}
