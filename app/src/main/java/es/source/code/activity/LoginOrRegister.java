package es.source.code.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.source.code.model.User;

public class LoginOrRegister extends AppCompatActivity implements View.OnClickListener {
    private Button btn_login, btn_return, btn_register;
    private ProgressBar progressBar;
    private int stepProgress;
    private EditText txt_username, txt_password;
    private User loginUser;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_or_register_layout);
        btn_login = (Button) findViewById(R.id.btn_Login);
        btn_return = (Button) findViewById(R.id.btn_return);
        btn_register = (Button) findViewById(R.id.btn_register);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        txt_username = (EditText) findViewById(R.id.etxt_username);
        txt_password = (EditText) findViewById(R.id.etxt_password);
        btn_return.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        stepProgress = progressBar.getMax() / 2;
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x111) {
                    int currentProgress = progressBar.getProgress();
                    progressBar.setProgress(currentProgress + stepProgress);
                } else {
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("from_login_button", "LoginSuccess");
                    bundle.putSerializable("login_user", loginUser);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }

    /**
     * 判断登录或者注册是否验证成功
     * @return 正则表达式的验证结果
     */
    public boolean isLoginOrRegisterSuccess()
    {
        String username = txt_username.getText().toString().trim();
        String password = txt_password.getText().toString().trim();
        String regEx = "^[0-9a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher_username = pattern.matcher(username);
        Matcher matcher_password = pattern.matcher(password);
        boolean is_username_success = matcher_username.matches();
        boolean is_password_success = matcher_password.matches();
        if (!is_username_success) {
            txt_username.setFocusable(true);
            txt_username.setFocusableInTouchMode(true);
            txt_username.requestFocus();
            txt_username.setError("输入内容不符合规则");
        }
        if (!is_password_success) {
            txt_password.setFocusable(true);
            txt_password.setFocusableInTouchMode(true);
            txt_password.requestFocus();
            txt_password.setError("输入内容不符合规则");
        }
        if(is_username_success && is_password_success)
        {
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Login:
                progressBar.setProgress(0);
                final int progressMax = progressBar.getMax();
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            Message message = new Message();
                            if (progressBar.getProgress() < progressMax) {
                                message.what = 0x111;
                                handler.sendMessage(message);
                            } else {
                                message.what = 0x110;
                                handler.sendMessage(message);
                                break;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                if (isLoginOrRegisterSuccess())
                {
                    progressBar.setVisibility(View.VISIBLE);
                    thread.start();
                    loginUser = new User();
                    loginUser.setUserName(txt_username.getText().toString().trim());
                    loginUser.setPassword(txt_password.getText().toString().trim());
                    loginUser.setOldUser(true);
//                    Toast.makeText(LoginOrRegister.this,"username: "+is_username_success+" password: "+is_password_success,Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_return:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("from_return_button", "Return");
                intent.putExtras(bundle);
                setResult(RESULT_CANCELED, intent);
                finish();
                break;
            case R.id.btn_register:
                if(isLoginOrRegisterSuccess())
                {
                    loginUser = new User();
                    loginUser.setUserName(txt_username.getText().toString().trim());
                    loginUser.setPassword(txt_password.getText().toString().trim());
                    loginUser.setOldUser(false);
                    Intent intent1=new Intent();
                    Bundle bundle1=new Bundle();
                    bundle1.putString("from_register_button","RegisterSuccess");
                    bundle1 .putSerializable("register_user",loginUser);
                    intent1 .putExtras(bundle1 );
                    setResult(2,intent1);
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
