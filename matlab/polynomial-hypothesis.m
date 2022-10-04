clear;

data = load('2022-09_sensor-wind-p1.csv');
hypothesis = load('polynomial-data.csv');

x_data = data(:,1);
y_data = data(:,2);
z_data = data(:,3);

x_hypothesis = hypothesis(:,1)
y_hypothesis = hypothesis(:,2)
z_hypothesis = hypothesis(:,3)

figure;
plot3(x_data, y_data, z_data, 'o');
hold on;
plot3(x_hypothesis, y_hypothesis, z_hypothesis, '+');
