clear;

data = load('2022-09_sensor-wind-p1.csv');
hypothesis = load('2022-09_sensor-hypothesis.csv');

x_data = data(:,1)
y_data = data(:,3)

m = length(hypothesis(:,2));

x = linspace(0, 1);
y_0 = hypothesis(1, 2);
y_m = hypothesis(m, 2);
h = y_0 - (y_0 - y_m) * x;

figure;
plot(x_data, y_data, 'x')
hold on;
plot(x, h);
