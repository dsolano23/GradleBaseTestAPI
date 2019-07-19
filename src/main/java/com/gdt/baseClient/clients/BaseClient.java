package com.gdt.baseClient.clients;

import com.gdt.baseClient.beans.RequestModelDtoOld;
import com.gdt.baseClient.beans.ResponseModelDto;

import java.io.IOException;

abstract class BaseClient {


    public abstract ResponseModelDto get(RequestModelDtoOld requestModelDTO) throws IOException;

    public abstract ResponseModelDto put(RequestModelDtoOld requestModelDTO) throws IOException;

    public abstract ResponseModelDto post(RequestModelDtoOld requestModelDTO) throws IOException;

    public abstract ResponseModelDto delete(RequestModelDtoOld requestModelDTO) throws IOException;

    public abstract ResponseModelDto postWithFiles(RequestModelDtoOld requestModelDTO) throws IOException;

}
