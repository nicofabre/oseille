package com.comencau.oseille.core;

import java.io.File;
import java.io.IOException;

/**
 * TODO
 *
 * @author Nicolas FABRE
 * @since 2018-03-27
 */
public interface ImportService {

    void importING(File file) throws IOException;

	void parseINGFile(File file) throws IOException;
}
