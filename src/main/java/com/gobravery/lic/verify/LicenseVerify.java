package com.gobravery.lic.verify;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.prefs.Preferences;

import com.gobravery.lic.License;
import com.gobravery.lic.VerifyInfo;
import com.gobravery.lic.exception.LicInvalidException;
import com.gobravery.lic.exception.LicVerificationException;
import com.gobravery.lic.holder.LicenseManagerHolder;

import de.schlichtherle.license.CipherParam;
import de.schlichtherle.license.DefaultCipherParam;
import de.schlichtherle.license.DefaultKeyStoreParam;
import de.schlichtherle.license.DefaultLicenseParam;
import de.schlichtherle.license.KeyStoreParam;
import de.schlichtherle.license.LicenseContent;
import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;

/**
 * VerifyLicense
 * @author melina
 */
public class LicenseVerify extends License{
    //common param
    private static String PUBLICALIAS = "";
    private static String STOREPWD = "";
    private static String SUBJECT = "";
    private static String licPath = "";
    private static String pubPath = "";
    
    public void setParam(String propertiesPath) {
        // 获取参数
        Properties prop = new Properties();
        InputStream in = getClass().getResourceAsStream(propertiesPath);
        try {
            prop.load(in);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        PUBLICALIAS = prop.getProperty("PUBLICALIAS");
        STOREPWD = prop.getProperty("STOREPWD");
        SUBJECT = prop.getProperty("SUBJECT");
        licPath = prop.getProperty("licPath");
        pubPath = prop.getProperty("pubPath");
    }

    public VerifyInfo verify() {
        /************** 证书使用者端执行 ******************/

        LicenseManager licenseManager = LicenseManagerHolder
                .getLicenseManager(initLicenseParams());
        // 安装证书
        try {
            licenseManager.install(new File(licPath));
            System.out.println("客户端安装证书成功!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new LicInvalidException("客户端证书安装失败!",e);
        }
        // 验证证书
        try {
            LicenseContent lc=licenseManager.verify();
            VerifyInfo vi=new VerifyInfo();
            System.out.println("客户端验证证书成功!");
            vi.setInfo(lc.getInfo());
            vi.setNotBefore(lc.getNotBefore());
            vi.setNotAfter(lc.getNotAfter());
            vi.setIssued(lc.getIssued());
            vi.setConsumerAmount(lc.getConsumerAmount());
            vi.setConsumerType(lc.getConsumerType());
            Date d=new Date();
            if(d.after(vi.getNotAfter())){
            	throw new LicVerificationException("客户端证书过期!");
            }
            return vi;
        } catch (Exception e) {
            e.printStackTrace();
            throw new LicVerificationException("客户端证书验证失效!",e);
        }
    }

    // 返回验证证书需要的参数
    private static LicenseParam initLicenseParams() {
        Preferences preference = Preferences
                .userNodeForPackage(LicenseVerify.class);
        CipherParam cipherParam = new DefaultCipherParam(CipherParamPw);

        KeyStoreParam privateStoreParam = new DefaultKeyStoreParam(
        		LicenseVerify.class, pubPath, PUBLICALIAS, STOREPWD, null);
        LicenseParam licenseParams = new DefaultLicenseParam(SUBJECT,
                preference, privateStoreParam, cipherParam);
        return licenseParams;
    }
}
