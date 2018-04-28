package com.myweb.service.impl;

import com.myweb.dao.jpa.hibernate.*;
import com.myweb.pojo.*;
import com.myweb.service.OneService;
import com.myweb.vo.GetReferUrlVo;
import com.utils.Result;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;


@Service("OneService")
@SuppressWarnings("All")
@Transactional(readOnly = true)
@PropertySource({"classpath:application.properties"})
public class OneServiceImpl implements OneService {

    private static final Logger logger = LogManager.getLogger(OneServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FisheryRepository fisheryRepository;

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private OpslogRepository opslogRepository;

    @Autowired
    private ReferRepository referRepository;

    @Value("${custom.location.img}")
    private String location;


    @Override
    @Transactional(value = "myTM", propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result registry(User user) {
        Result result = new Result();
        if (StringUtils.isBlank(user.getAddress()) || StringUtils.isBlank(user.getSignMessage()) || StringUtils.isBlank(user.getSignedMessage())) {
            result.setMessage("The required parameters are empty!");
            return result;
        }
        //判断签名
        //
        if (userRepository.findByAddress(user.getAddress()).size() > 0) {
            result.setMessage("This address has been registered!");
            return result;
        }
        user.setReferCode(RandomStringUtils.randomAlphanumeric(8));
        User savedUser = userRepository.save(user);
        result.setStatus(1);
        result.setData(savedUser);
        return result;
    }

    @Override
    @Transactional(value = "myTM", propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result setNickName(User user) {
        Result result = new Result();
        if (StringUtils.isBlank(user.getNickName()) || StringUtils.isBlank(user.getAddress())) {
            result.setMessage("The required parameters are empty!");
            return result;
        }
        List<User> userList = userRepository.findByAddress(user.getAddress());
        if (userList.size() == 0 || userList.size() > 1) {
            result.setMessage("The user cant be found!");
            return result;
        }
        User savedUser = userList.get(0);
        savedUser.setNickName(user.getNickName());
        User savedUserIn = userRepository.save(savedUser);
        result.setStatus(1);
        result.setData(savedUserIn);
        return result;
    }

    @Override
    @Transactional(value = "myTM", propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result setAvatar(MultipartFile multipartFile, User user) {
        Result result = new Result();
        if (multipartFile == null || multipartFile.isEmpty() || StringUtils.isBlank(multipartFile.getOriginalFilename()) || StringUtils.isBlank(user.getAddress())) {
            result.setMessage("The required parameters are empty!");
            return result;
        }
        String contentType = multipartFile.getContentType();
        if (!contentType.contains("")) {
            result.setMessage("The required parameters are empty!");
            return result;
        }
        String fileName = multipartFile.getOriginalFilename();
        //处理图片
        String fileNameIn = null;
        try {
            fileNameIn = saveImg(multipartFile, fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()));
            if (StringUtils.isNotBlank(fileNameIn)) {
                List<User> userList = userRepository.findByAddress(user.getAddress());
                if (userList.size() == 0 || userList.size() > 1) {
                    result.setMessage("The user cant be found!");
                    return result;
                }
                User savedUser = userList.get(0);
                savedUser.setAvatar(fileNameIn);
                userRepository.save(savedUser);
                result.setStatus(1);
                result.setData(savedUser);
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result getReferUrl(User user) {
        Result result = new Result();
        if (StringUtils.isBlank(user.getAddress())) {
            result.setMessage("The required parameters are empty!");
            return result;
        }
        List<User> userList = userRepository.findByAddress(user.getAddress());
        if (userList.size() == 0) {
            User user1 = new User();
            user1.setAddress(user.getAddress());
            user1.setReferCode(RandomStringUtils.randomAlphanumeric(8));
            User savedUser1 = userRepository.save(user1);
            userList.add(savedUser1);
        }
        User savedUser = userList.get(0);
        GetReferUrlVo gruv = new GetReferUrlVo();
        gruv.setReferCode(savedUser.getReferCode());
        gruv.setReferCount(referRepository.countAllByReferCode(savedUser.getReferCode()));
        gruv.setSumReferFee(referRepository.sumReferFeeByReferCode(savedUser.getReferCode()));
        result.setStatus(1);
        result.setData(gruv);
        return result;
    }

    @Override
    @Transactional(value = "myTM", propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result setName(Fishery fishery) {
        Result result = new Result();
        if (StringUtils.isBlank(fishery.getName()) || fishery.getId() == 0) {
            result.setMessage("The required parameters are empty!");
            return result;
        }
        Fishery savedFishery = fisheryRepository.findOne(fishery.getId());
        if (savedFishery == null) {
            savedFishery = new Fishery();
            savedFishery.setId(fishery.getId());
            fisheryRepository.save(savedFishery);
            savedFishery = fisheryRepository.findOne(fishery.getId());
        }
        savedFishery.setName(fishery.getName());
        fisheryRepository.save(savedFishery);
        result.setStatus(1);
        result.setData(savedFishery);
        return result;
    }

    @Override
    @Transactional(value = "myTM", propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result bind(Fishery fishery) {
        Result result = new Result();
        if (StringUtils.isBlank(fishery.getBindAddress()) || fishery.getId() == 0) {
            result.setMessage("The required parameters are empty!");
            return result;
        }
        Fishery savedFishery = fisheryRepository.findOne(fishery.getId());
        if (savedFishery == null) {
            savedFishery = new Fishery();
            savedFishery.setId(fishery.getId());
            fisheryRepository.save(savedFishery);
            savedFishery = fisheryRepository.findOne(fishery.getId());
        }
        savedFishery.setBindAddress(fishery.getBindAddress());
        if (StringUtils.isBlank(savedFishery.getBindAddress())) {
            savedFishery.setBindStatus("first binding");
        } else {
            savedFishery.setBindStatus("binding");
        }
        fisheryRepository.save(savedFishery);
        result.setStatus(1);
        result.setData(savedFishery);
        return result;
    }

    @Override
    @Transactional(value = "myTM", propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result unbind(Fishery fishery) {
        Result result = new Result();
        if (fishery.getId() == 0) {
            result.setMessage("The required parameters are empty!");
            return result;
        }
        Fishery savedFishery = fisheryRepository.findOne(fishery.getId());
        if (savedFishery == null) {
            result.setMessage("The Fishery cant be found!");
            return result;
        }
        savedFishery.setBindStatus("unbinding");
        fisheryRepository.save(savedFishery);
        result.setStatus(1);
        result.setData(savedFishery);
        return result;
    }

    @Override
    @Transactional(value = "myTM", propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result sell(Market market) {
        Result result = new Result();
        if (market.getFisheryId() == null || market.getStartPrice() == null || market.getStopPrice() == null || market.getSellDuration() == null) {
            result.setMessage("The required parameters are empty!");
            return result;
        }
        Fishery savedFishery = fisheryRepository.findOne(market.getFisheryId());
        if (savedFishery == null) {
            result.setMessage("The Fishery cant be found!");
            return result;
        }
        if (savedFishery.getSellStatus() != null && savedFishery.getSellStatus().equals("selling")) {
            result.setMessage("The Fishery is selling in market!");
            return result;
        }
        savedFishery.setSellStatus("selling");
        fisheryRepository.save(savedFishery);
        market.setSellStatus("selling");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long currentTime = System.currentTimeMillis();
        market.setSellStartTime(df.format(currentTime));
        market.setFavorCount(0);
        market.setId(0);
        Market savedMarket = marketRepository.save(market);
        result.setStatus(1);
        result.setData(savedMarket);
        return result;
    }

    @Override
    @Transactional(value = "myTM", propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result unsell(Market market) {
        Result result = new Result();
        if (market.getFisheryId() == 0) {
            result.setMessage("The required parameters are empty!");
            return result;
        }
        Fishery savedFishery = fisheryRepository.findOne(market.getFisheryId());
        if (savedFishery == null) {
            result.setMessage("The Fishery cant be found!");
            return result;
        }
        if (savedFishery.getSellStatus() != null && savedFishery.getSellStatus().equals("unselled")) {
            result.setMessage("The Fishery is unselled in market!");
            return result;
        }
        savedFishery.setSellStatus("unselled");
        fisheryRepository.save(savedFishery);
        marketRepository.delete(marketRepository.findAllByFisheryId(savedFishery.getId()));
        result.setStatus(1);
        result.setData(savedFishery);
        return result;
    }

    @Override
    @Transactional(value = "myTM", propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result buy(Fishery fishery, Refer refer) {
        Result result = new Result();
        if (StringUtils.isNotBlank(refer.getReferCode())) {
            refer.setId(0);
            refer.setStatus("buy");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long currentTime = System.currentTimeMillis();
            refer.setCreateTime(df.format(currentTime));
            referRepository.save(refer);
            logger.info("------------------------------------------------------------------------------------------");
            logger.info("推广码:"+refer.getReferCode()+"佣金:"+refer.getReferFee()+"创建时间:"+refer.getCreateTime());
            logger.info("------------------------------------------------------------------------------------------");
        }
        Fishery savedFishery = fisheryRepository.findOne(fishery.getId());
        if (savedFishery == null) {
            savedFishery = new Fishery();
            savedFishery.setId(fishery.getId());
        }
        savedFishery.setSellStatus(null);
        savedFishery.setAddress(fishery.getAddress());
        fisheryRepository.save(savedFishery);
        if (fishery.getId() != 0) {
            marketRepository.delete(marketRepository.findAllByFisheryId(fishery.getId()));
        }
        result.setStatus(1);
        result.setData(savedFishery);
        return result;
    }

    @Override
    @Transactional(value = "myTM", propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result favor(Market market) {
        Result result = new Result();
        if (market.getId() == 0) {
            result.setMessage("The required parameters are empty!");
            return result;
        }
        Market savedMarket = marketRepository.findOne(market.getId());
        if (savedMarket == null) {
            result.setMessage("The Market cant be found!");
            return result;
        }
        savedMarket.setFavorCount(savedMarket.getFavorCount() + 1);
        marketRepository.save(savedMarket);
        result.setStatus(1);
        result.setData(savedMarket);
        return result;
    }

    @Override
    public Result get(User user) {
        Result result = new Result();
        if (StringUtils.isBlank(user.getAddress())) {
            result.setMessage("The required parameters are empty!");
            return result;
        }
        List<User> savedUserList = userRepository.findByAddress(user.getAddress());
        if (savedUserList == null || savedUserList.size() == 0) {
            result.setMessage("The User cant be found!");
            return result;
        }
        result.setStatus(1);
        result.setData(savedUserList.get(0));
        return result;
    }

    @Override
    public Result query(Opslog opslog, Pageable pageable) {
        Result result = new Result();
        if (StringUtils.isBlank(opslog.getAddress())) {
            result.setMessage("The required parameters are empty!");
            return result;
        }
        Page<Opslog> opslogPage = opslogRepository.findAllByAddress(opslog.getAddress(), pageable);
        result.setStatus(1);
        result.setData(opslogPage);
        return result;
    }

    @Override
    @Transactional(value = "myTM", propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result createLog(Opslog opslog) {
        Result result = new Result();
        if (StringUtils.isBlank(opslog.getAddress())) {
            result.setMessage("The required parameters are empty!");
            return result;
        }
        opslog.setId(0);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long currentTime = System.currentTimeMillis();
        opslog.setActionTime(df.format(currentTime));
        opslogRepository.save(opslog);
        result.setStatus(1);
        return result;
    }

    @Override
    public Result getFishery(Fishery fishery) {
        Result result = new Result();
        if (fishery.getId() == 0) {
            result.setMessage("The required parameters are empty!");
            return result;
        }
        Fishery savedFishery = fisheryRepository.findOne(fishery.getId());
        if (savedFishery == null) {
            result.setMessage("The Fishery cant be found!");
            return result;
        }
        result.setStatus(1);
        result.setData(savedFishery);
        return result;
    }

    @Override
    @Transactional(value = "myTM", propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result createLogAsResult(Opslog opslog) {
        Result result = new Result();
        if (StringUtils.isBlank(opslog.getAddress())) {
            result.setMessage("The required parameters are empty!");
            return result;
        }
        opslog.setId(0);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long currentTime = System.currentTimeMillis();
        opslog.setActionTime(df.format(currentTime));
        opslogRepository.save(opslog);
        result.setStatus(1);
        result.setData(opslog);
        return result;
    }

    /**
     * 保存文件，直接以multipartFile形式
     *
     * @param multipartFile
     * @param path          文件保存绝对路径
     * @return 返回文件名
     * @throws IOException
     */
    public String saveImg(MultipartFile multipartFile, String fileType) throws IOException {
        File file = new File(location);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        String fileName = RandomStringUtils.randomAlphanumeric(8) + "." + fileType;
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(location + File.separator + fileName));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        return fileName;
    }
}
