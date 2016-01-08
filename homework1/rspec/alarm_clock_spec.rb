require 'java'
java_import 'hw1.AlarmClock'

describe Java::Hw1::AlarmClock do 
  before(:each) do
    @alarm_clock = AlarmClock.new 
  end
  
  let(:c) { @alarm_clock }
  subject { c }
  
  describe "class should be alarm clock class" do
    subject { c.class }
    it { should eql Java::Hw1::AlarmClock }
  end
  
  it { should respond_to(:advanceTime) }
  it { should respond_to(:alarmOff) }
  it { should respond_to(:alarmOn) }
  it { should respond_to(:getAlarmTime) }
  it { should respond_to(:getAlarmTimeAsString) }
  it { should respond_to(:getClockTime) }
  it { should respond_to(:getClockTimeAsString) }
  it { should respond_to(:getEffectiveAlarmTime) }
  it { should respond_to(:getEffectiveAlarmTimeAsString) }
  it { should respond_to(:isRinging) }
  it { should respond_to(:setAlarmTime) }
  it { should respond_to(:setTime) }
  it { should respond_to(:snooze) }
  
  
  describe "Minutes per day" do
  end
  
  describe "constructor with given hours and minutes" do
    let(:c){ AlarmClock.new(3,10) }
    subject { c.getClockTime }
    it { should eql 190 }
  end 
  
  describe "advanceTime should return void" do
    subject { c.advanceTime(60) }
    it { should eql nil }
  end
  
  describe "alarmOff should return void" do
    subject { c.alarmOff }
    
    it { should eql nil }
  end
  
  describe "alarmOn should return void" do
    subject { c.alarmOn }
    
    it { should eql nil }
  end
  
  describe "getAlarmTime should return a int" do
    subject { c.getAlarmTime.class }
    
    it { should eql Fixnum }
  end
  
  describe "getAlarmTimeAsString should return a String" do
    subject { c.getAlarmTimeAsString.class }
    
    it { should eql String }
  end
  
  describe "getClockTime should return a int" do
    subject { c.getClockTime.class }
    
    it { should eql Fixnum }
  end
  
  describe "getClockTimeAsString should return a String" do
    subject { c.getClockTimeAsString.class }
    
    it { should eql String }
  end
  
  describe "getEffectiveAlarmTime should return a int" do
    subject { c.getEffectiveAlarmTime.class }
    
    it { should eql Fixnum }
  end
  
  describe "getEffectiveAlarmTimeAsString should return a String" do
    subject { c.getEffectiveAlarmTimeAsString.class }
    
    it { should eql String }
  end
  
  describe "isRinging should return a boolean" do
    subject { c.isRinging.class }
    it { should eql FalseClass or should eql TrueClass }
  end
  
  describe "setAlarmTime should return void" do
    subject { c.setAlarmTime(2,50) }
    
    it { should eql nil }
  end
  
  describe "setTime should return void" do
    subject { c.setTime(2,50) }
    
    it { should eql nil }
  end
  
  describe "snooze should return void" do
    subject { c.snooze }
    
    it { should eql nil }
  end 
  
  describe "a new alarm clock without a specified clock time should not be ringing" do
    subject { c.isRinging }
    it { should eql false }
  end
  
  describe "clock time should be 00:00 when a new clock is created" do 
    subject { c.getClockTimeAsString }
    it { should eql "00:00" }
  end
  
  describe "clock time should be midnigt on a new alarm clock without params" do
    subject { c.getClockTime }
    it { should eql 0 }
  end
  
  describe "getAlarmTime should return 60 on a new alarm clock without params"  do
  end
  
  describe "advnaceTime should increase the number of minutes past midnight by the given number" do
    before {c.advanceTime(0, 30) }
    subject { c.getClockTime }
    it { should eql 30 }
  end
  
  describe "advnaceTime should increase the number of minutes and hours past midnight by the given number of hours and minute" do
    before { c.advanceTime(1, 30) }
    subject { c.getClockTime }
    it { should eql 90 }
  end
  
  
  describe "advnaceTime should increase the number of minutes and hours past midnight by the given number of hours and minute" do
    before do
      c.advanceTime(1, 30)
      c.advanceTime(30)
    end
    subject { c.getClockTime }
    it { should eql 120 }
  end
  
  describe "advnaceTime should increase the number of minutes and hours past midnight by the given number of hours and minute" do
    before do
      c.setTime(0,0)
      c.advanceTime(25, 30)
    end
    subject { c.getClockTime }
    it { should eql 90 }
  end
  
  describe "advnaceTime should increase the number of minutes and hours past midnight by the given number of hours and minute" do
    before do
      c.setTime(0,0)
      c.advanceTime(1530)
    end
    subject { c.getClockTime }
    it { should eql 90 }
  end
  
  describe "setTime should override the current time and repalce with new time" do
    before { c.setTime(15, 30) }
    subject { c.getClockTime }
    it { should eql 930 }
  end 
  
  describe "setTime should not should the alarm" do
    before do
      c.setAlarmTime(15,30)
      c.alarmOn
      c.setTime(15, 30) 
     end
    subject { c.getClockTime }
    it { should eql 930 }
  end 
  
  describe "getAlarmTime should return 60 on a new alarm clock" do
    before { c.setAlarmTime(2, 30) }
    subject { c.getAlarmTime }
    it { should eql 150 }
  end
  
  describe "getAlarmTimeAsString should return 2:38 after two hours and 38 minutes pass" do
    before { c.setAlarmTime(2, 38) }
    subject { c.getAlarmTimeAsString }
    it { should eql "02:38" }
  end
  
  describe "the alarm should be ringing if the current is the alarm time" do
    before do
      c.setAlarmTime(2, 30)
       c.alarmOn()
      c.setTime(0,0)
      c.advanceTime(2,30)
    end
    
    subject { c.isRinging }
    it { should eql true }
  end
  
  describe "the alarm should not be ringing if the current is the alarm time is set via the AlarmTime set" do
    before do
      c.setAlarmTime(0, 30)
      c.setTime(0,30)
    end
    
    subject { c.isRinging }
    it { should_not eql true }
  end
  
  describe "the alarm should not be ringing if the current is the alarm time is set via the AlarmTime set" do
    before do
      c.setAlarmTime(0, 30)
      c.setTime(0,30)
    end
    
    subject { c.getEffectiveAlarmTime }
    it { should eql 30 }
  end
  
  describe "the alarm should be ringing if the current is the alarm time" do
    before do
      c.setAlarmTime(2, 30)
      c.setTime(0,0)
      c.alarmOn()
      c.advanceTime(2, 30)
    end
    
    subject { c.isRinging }
    it { should eql true }
  end
  
  describe "the alarm should not be ringing if the current is the alarm time and the alarm is off" do
    before do
      c.setAlarmTime(2, 30)
      c.setTime(0,0)
      c.advanceTime(2, 30)
      c.alarmOff
    end
    
    subject { c.isRinging }
    it { should_not eql true }
  end
  
  describe 'ring snooze ring' do
    before do
      c.setAlarmTime(2, 30)
       c.alarmOn
      c.advanceTime(2, 30)
      c.snooze
      c.advanceTime(9)
    end
    
    subject { c.isRinging }
    it { should eql true }
    
  end
  
  describe 'ring snooze' do
    before do
      c.alarmOn
      c.setAlarmTime(2, 30)
      c.advanceTime(2, 30)
      c.snooze
    end
    
    subject { c.isRinging }
    it { should_not eql true }
  end
  
  describe 'pass alarm time with alarmOff' do
    before do
      c.setAlarmTime(2, 30)
      c.advanceTime(2, 30)
    end
    
    subject { c.isRinging }
    it { should_not eql true }
  end
  
  describe "ring it 30 times with snonze" do
    before do
      c.setTime(0,0)
      30.times do
        c.setAlarmTime(2, 30)
        c.alarmOn()
        c.advanceTime(30)
        c.advanceTime(30)
        c.advanceTime(23,0  )
      end
    end
    
    describe "the alarm should still ring if time is advnaced over the alarm time" do
      before do
        c.setAlarmTime(5, 30)
        c.setTime(0,0)
        c.advanceTime(6, 30)
      end
    end
    
    subject { c.isRinging  }
    it { should eql true }
  end
  
  describe "alarm should ring when going into the next day" do
    before do
      c.setTime(23,15)
      c.alarmOn;
      c.setAlarmTime(23,30)
      c.advanceTime(60)
    end
    
    subject { c.isRinging }
    it { should eql true}
  end
  
  describe "alarm should not ring when clock time is alarm time then advanced" do
    before do
      c.setTime(23,30)
      c.alarmOn;
      c.setAlarmTime(23,30)
      c.advanceTime(60)
    end
    
    subject { c.isRinging }
    it { should_not eql true}
  end
  
  describe "alarm should ring when clock time is equal to the alarm time then advanced by 24-hours" do
    before do
      c.setTime(23,30)
      c.alarmOn;
      c.setAlarmTime(23,30)
      c.advanceTime(24,0)
    end
    
    subject { c.isRinging }
    it { should eql true}
  end
  
  describe "alarm should ring when clock time is equal to the alarm time then advanced by 48-hours" do
    before do
      c.setTime(23,30)
      c.alarmOn;
      c.setAlarmTime(23,30)
      c.advanceTime(48,0)
    end
    
    subject { c.isRinging }
    it { should eql true}
  end
  
  describe "snooze should not affect effectivetime when alarmo is set to not ring" do
    before do
      c.setTime(23,30)
      c.setAlarmTime(23,30)
    end
    
    subject { c.getEffectiveAlarmTime }
    it { should eql 1410 }
  end

  describe "snooze should not affect effectivetime when alarm is not ringing" do
    before do
      c.setTime(23,20)
      c.alarmOn
      c.setAlarmTime(23,30)
      c.advanceTime(10)
      c.snooze
      c.snooze
    end
    
    subject { c.getEffectiveAlarmTime }
    it { should eql 1419 }
  end
end