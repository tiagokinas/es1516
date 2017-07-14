package pt.tecnico.mydrive.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;


import org.junit.Test;

import pt.tecnico.mydrive.domain.*;
import pt.tecnico.mydrive.exception.*;

import java.util.Arrays;
import java.util.List;

public class ListDirectoryTest extends AbstractServiceTest {

    private String type = "Plain File";
    private String name = "ola";
    private String name2 = "adeus";
    private String content = "ola mundo";
    private String content2 = "adeus lua";
    private long userToken; //nao pode ser null. Get da sessao?

    private PlainFile f;
    private PlainFile f2;
    private Directory d;
    private MyDrive md;
    private Session s;
    private User u;

    protected void populate() {
        MyDrive md = MyDrive.getInstance();
        f = new PlainFile(md, name, content);
        f2 = new PlainFile(md, name2, content2);
        u = s.getUserInSession();
        userToken = s.getUserInSession().getMyToken();
        s = u.getSession();
        d = s.getWorkDir();
    }

    @Test
    public void ListDirectoryTest() {
        ListDirectoryService service = new ListDirectoryService(userToken);

        service.execute();

    }

   

    @Test
    public void success() {
        List<String> actual = Arrays.asList(name,name2);
        List<String> expected = Arrays.asList("ola", "adeus");

        assertEquals(actual,expected);
    }
}