package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {
    /**
     * Test when the edge case when the both supported and enabled protocols are null.
     */
    @Test
    public void preparedSocket_NullProtocols()  {
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket mockedSocket = mock(SSLSocket.class);
        when(mockedSocket.getSupportedProtocols()).thenReturn(null);
        when(mockedSocket.getEnabledProtocols()).thenReturn(null);
        f.prepareSocket(mockedSocket);
    }

    @Test
    public void typical()  {
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket mockedSocket = mock(SSLSocket.class);
        when(mockedSocket.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(mockedSocket.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));
        f.prepareSocket(mockedSocket);
        verify(mockedSocket).setEnabledProtocols(any());;
    }


    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }

}