package com.example.demo;

import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

@Service
public class XmlService {

    private static final String XML_FILE_PATH = "users.xml";

    public String marshal(Users users) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Users.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter writer = new StringWriter();
        marshaller.marshal(users, writer);
        return writer.toString();
    }

    public Users unmarshal(String xml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Users.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Users) unmarshaller.unmarshal(new StringReader(xml));
    }

    public Users readUsersFromFile() throws JAXBException {
        File file = new File(XML_FILE_PATH);
        if (!file.exists()) {
            return new Users();
        }
        JAXBContext context = JAXBContext.newInstance(Users.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Users) unmarshaller.unmarshal(file);
    }

    public void writeUsersToFile(Users users) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Users.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(users, new File(XML_FILE_PATH));
    }
}