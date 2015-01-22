package com.silu.game.service;

import com.silu.game.exception.ServerException;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * Created by silu on 15-1-14.
 */
public interface FileService {
    String storeFile(InputStream inputStream,String fileName,long size) throws ServerException;

    void getfile(HttpServletResponse res, String fileId,Long range) throws ServerException;
}
