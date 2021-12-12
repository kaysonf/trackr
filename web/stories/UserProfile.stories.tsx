import React from 'react';
import {ComponentMeta, ComponentStory} from '@storybook/react';
import {UserProfile} from "../components/user/UserProfile";

export default {
    title: 'User/ProfileCard',
    component: UserProfile,
} as ComponentMeta<typeof UserProfile>;

const Template: ComponentStory<typeof UserProfile> = (args) => <UserProfile {...args} />;

export const ProfileCard = Template.bind({});

ProfileCard.args = {
    id: 'some uuid',
    handle: 'kayson',
    email: 'kayson@trackr.com'
}
